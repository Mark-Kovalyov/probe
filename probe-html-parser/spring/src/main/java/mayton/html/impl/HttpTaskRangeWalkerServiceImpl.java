package mayton.html.impl;

import mayton.html.*;
import mayton.html.entities.MemberInfo;
import mayton.html.entities.TaskInfo;
import mayton.html.utils.SqlRuUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkNotNull;
import static mayton.html.TaskState.*;

/**
 * Route's mebers by id ranges
 */
@Component("TaskRange")
public class HttpTaskRangeWalkerServiceImpl implements WalkerService {

    static Logger logger = LogManager.getLogger(HttpTaskRangeWalkerServiceImpl.class);

    @Autowired
    private MemberWriterService memberWriterService;

    @Autowired
    private DynamicDictionaryService dynamicDictionaryService;

    @Autowired
    private Config config;

    @Autowired
    @Qualifier("JDBCTaskProviderImpl")
    public TaskProvider taskProvider;

    private Range<Integer> ignoreMemberRange;

    private Set<String> ignoreNickNamesSet;

    @PostConstruct
    public void postConstruct() {
        int begin = 0;
        int end = 1000;
        ignoreMemberRange = Range.between(begin, end);
        LinkedHashMap<String, Object> walker = (LinkedHashMap<String, Object>) config.getRoot().get("walker");
        LinkedHashMap<String, Object> ignoreMembers = (LinkedHashMap<String, Object>) walker.get("ignoreMembers");
        List<String> byNickNames = (ArrayList<String>) ignoreMembers.get("byNickNames");
        ignoreNickNamesSet = new HashSet<>(byNickNames);
    }

    public Optional<MemberInfo> processMainFields(@NotNull Document doc, int mid) {
        logger.trace("title = {}", doc.title());
        MemberInfo memberInfo = new MemberInfo(mid);

        Element tableElement = doc
                .selectFirst("body")
                    .selectFirst("div")
                        .select("div").get(0)
                            .selectFirst("center")
                                .selectFirst("table[id=author_info]");

        if (tableElement == null) {
            logger.warn("Unable to get member info from mid = {}", mid);
            return Optional.empty();
        }

        Element tbodyElement = tableElement.selectFirst("tbody[id=tab_container]");
        Element internalTable = tbodyElement.selectFirst("table[id=info_page]");
        for (Element element : internalTable.selectFirst("tbody").select("tr")) {
            //logger.trace("::: node {}", element.toString());
            Elements tds = element.select("td");
            if (tds != null && tds.size() >= 2) {
                Element keyElement = tds.get(0);   //  Имя
                Element valueElement = tds.get(1); //  judge
                if (keyElement != null && valueElement != null) {
                    Element strongElement = keyElement.selectFirst("strong");
                    if (strongElement != null && "Имя".equals(strongElement.text())) {
                        String nickName = valueElement.selectFirst("strong").text();
                        logger.info("Name : '{}'", nickName);
                        memberInfo.setNickname(nickName);
                    } else if (strongElement != null && "Зарегистрирован:".equals(strongElement.text())) {
                        String registered = valueElement.text();
                        logger.info("Зарегистрирован : '{}'", registered);
                        if (!StringUtils.isBlank(registered)) {
                            memberInfo.setRegistered(SqlRuUtils.parseSqlRuDate(registered));
                        }
                    } else if (strongElement != null && "Сообщений:".equals(strongElement.text())) {
                        String messages = valueElement.text();
                        int messagesInt = Integer.parseInt(messages);
                        logger.info("Messages : {}", messagesInt);
                        memberInfo.setMessages(messagesInt);
                    } else if (strongElement !=null && "E-Mail:".equals(strongElement.text())) {
                        String email = valueElement.text();
                        logger.info("email : '{}'", email);
                        if (!StringUtils.isBlank(email) && !"скрыт".equals(email)) {
                            memberInfo.setEmail(email);
                        }
                    } else if (strongElement != null && "Последнее сообщение:".equals(strongElement.text())) {
                        String lastUpdate = valueElement.text();
                        logger.info("Последнее сообщение : '{}'", lastUpdate);
                        if (!StringUtils.isBlank(lastUpdate)) {
                            memberInfo.setLastUpdate(SqlRuUtils.parseSqlRuDate(lastUpdate));
                        }
                    }
                }
            }
        }
        if (memberInfo.getNickname() == null) {
            throw new RuntimeException("Unable to init mandatory 'nickName' field!");
        }
        if (ignoreNickNamesSet.contains(memberInfo.getNickname())) {
            logger.warn("This membername {} is in ignore list", memberInfo.getNickname());
            return Optional.empty();
        }
        memberInfo.setState("REGISTERED");
        return Optional.of(memberInfo);
    }


    public MemberInfo processHistogram(@NotNull Document doc, int mid, @NotNull MemberInfo memberInfo) throws SQLException {
        Element memberMessagesToForumsElement = doc.select("table[id=member_messages_to_forums]").first();

        Elements histogramTableRows = memberMessagesToForumsElement
                .selectFirst("tbody")
                    .selectFirst("tr")
                        .selectFirst("td")
                            .selectFirst("table")
                                .selectFirst("tbody")
                                    .select("tr");

        checkNotNull(histogramTableRows);
        LinkedHashMap<Integer, Double> linkedHashMap = new LinkedHashMap<>();
        for (Element element : histogramTableRows) {
            Elements tdElements = element.select("td");
            String key = tdElements.get(0).text();
            String value = tdElements.get(2).text();
            if (!"Форум".equals(key)) {
                int id = dynamicDictionaryService.getOrCreateEntityId(key);
                linkedHashMap.put(id, SqlRuUtils.parsePecentFormattedValue(value));
            }
        }
        memberInfo.setMessagesDistibution(linkedHashMap);
        return memberInfo;
    }

    private boolean isInIgnoredRanges(int mid) {
        // TODO: Implement multiple ranges
        return ignoreMemberRange.contains(mid);
    }

    public void processMember(@NotNull String url, int mid) throws SQLException {
        ThreadContext.put("mid", "" + mid);
        if (isInIgnoredRanges(mid)) {
            logger.warn("Skipped mid = {} downloading due to 'ignore range' rule", mid);
            ThreadContext.remove("mid");
        } else {
            String memberInfoUrl = url + "/forum/memberinfo.aspx?mid=" + mid;
            logger.trace(":: member url = {}", memberInfoUrl);
            Connection connect = Jsoup.connect(memberInfoUrl);
            Document doc;
            try {
                doc = connect.get();
                Optional<MemberInfo> memberInfoOptional = processMainFields(doc, mid);
                if (memberInfoOptional.isPresent()) {
                    MemberInfo memberInfo = memberInfoOptional.get();
                    MemberInfo memberInfo2 = processHistogram(doc, mid, memberInfo);
                    memberWriterService.upsert(memberInfo2);
                    logger.info(":: upsert {}", memberInfo2.toString());
                }
            } catch (IOException ex) {
                logger.warn("IOException during processMember mid=" + mid, ex);
            } finally {
                ThreadContext.remove("mid");
            }
        }
    }



    public void processSeries(@NotNull String url, Iterable<Integer> mids) throws SQLException {
        Iterator<Integer> i = mids.iterator();
        Random r = new Random();
        while (i.hasNext()) {
            processMember(url, i.next());
            SqlRuUtils.sleep(10 + r.nextInt(5));
        }
    }

    @Override
    public void walk(@NotNull String url) {
        logger.info(":: begin walk for url = '{}'", url);
        try {
            //walkVIPs(url, (ArrayList<Integer>)((LinkedHashMap)(config.getRoot().get("walker"))).get("VIPS"));
            walkRegularMembers(url);
        } catch (SQLException ex) {
            logger.error(":: fatal error ", ex);
        }
    }

    private void walkVIPs(@NotNull String url, @NotNull List<Integer> asList) throws SQLException {
        logger.info(":: walk VIP-s");
        ThreadContext.put("taskId", "VIP task");
        processSeries(url, asList);
        ThreadContext.remove("taskId");
        logger.info(":: end of walk VIP-s");
    }

    private void walkRegularMembers(@NotNull String url) throws SQLException {
        logger.info(":: walk regular members");
        Optional<TaskInfo> taskInfoOptional;
        while ((taskInfoOptional = taskProvider.provideNextTask()).isPresent()) {
            TaskInfo taskInfo = taskInfoOptional.get();
            if (taskInfo.getTaskState().equals(READY)) {
                int id = taskInfo.getId();
                ThreadContext.put("taskId", "" + id);
                int start = taskInfo.getMemberStart();
                int end = taskInfo.getMemberEnd();
                List<Integer> midsLinearSequence = IntStream.range(start, end)
                        .boxed()
                        .collect(Collectors.toList());

                Collections.shuffle(midsLinearSequence);

                taskProvider.updateTaskStatus(taskInfo, IN_PROGRESS);
                processSeries(url, midsLinearSequence);
                taskProvider.updateTaskStatus(taskInfo, FINISHED);
                ThreadContext.remove("taskId");
            }
        }
        logger.info(":: end of walk regular members");

    }

}
