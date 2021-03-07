package mayton.html.impl;

import mayton.html.DynamicDictionaryService;
import mayton.html.entities.MemberInfo;
import mayton.html.MemberInfoService;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

@SuppressWarnings("java:S1192")
@Component
public class MemberInfoServiceImpl implements MemberInfoService {

    static Logger logger = LogManager.getLogger(MemberInfoServiceImpl.class);

    @Autowired
    private DynamicDictionaryService dynamicDictionaryService;

    private Range<Integer> ignoreMemberRange;

    private Set<String> ignoreNickNamesSet;

    @Value("${httpUrl}")
    private String httpUrl;

    @PostConstruct
    public void postConstruct() {
        // TODO: Implement
        int begin = 0;
        int end = 1000;
        ignoreMemberRange = Range.between(begin, end);
        //LinkedHashMap<String, Object> walker = (LinkedHashMap<String, Object>) config.getRoot().get("walker");
        //LinkedHashMap<String, Object> ignoreMembers = (LinkedHashMap<String, Object>) walker.get("ignoreMembers");
        //List<String> byNickNames = (ArrayList<String>) ignoreMembers.get("byNickNames");
        //ignoreNickNamesSet = new HashSet<>(byNickNames);
        //httpUrl = (String) walker.get("url");
    }

    public void upgradeHistogram(@NotNull Document doc, @NotNull MemberInfo memberInfo) throws SQLException {
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
    }

    public Optional<MemberInfo> parseMainFields(@NotNull Document doc, int mid) {
        if (logger.isTraceEnabled()) logger.trace("title = {}", doc.title());
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

    private boolean isInIgnoredRanges(int mid) {
        // TODO: Implement multiple ranges
        return ignoreMemberRange.contains(mid);
    }



    @Override
    public Optional<MemberInfo> getMemberInfo(int mid) throws SQLException {
        ThreadContext.put("mid", "" + mid);
        if (isInIgnoredRanges(mid)) {
            logger.warn("Skipped mid = {} downloading due to 'ignore range' rule", mid);
            ThreadContext.remove("mid");
            return Optional.empty();
        } else {
            String memberInfoUrl = httpUrl + "/forum/memberinfo.aspx?mid=" + mid;
            logger.trace(":: member url = {}", memberInfoUrl);
            Connection connect = Jsoup.connect(memberInfoUrl);
            Document doc;
            try {
                doc = connect.get();
                Optional<MemberInfo> memberInfoOptional = parseMainFields(doc, mid);
                if (memberInfoOptional.isPresent()) {
                    MemberInfo memberInfo = memberInfoOptional.get();
                    upgradeHistogram(doc, memberInfo);
                    return Optional.of(memberInfo);
                }
                return Optional.empty();
            } catch (IOException ex) {
                logger.warn("IOException during processMember mid={}", mid, ex);
                return Optional.empty();
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } finally {
                ThreadContext.remove("mid");
            }
        }
    }

}
