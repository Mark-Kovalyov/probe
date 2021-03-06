package mayton.html.impl;

import mayton.html.*;
import mayton.html.entities.MemberInfo;
import mayton.html.utils.SqlRuUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.*;

@SuppressWarnings("java:S1192")
@Component("vipwalker")
@ConfigurationProperties(prefix = "walker.vipwalker")
public class HttpVipWalkerServiceImpl implements WalkerService {

    static Logger logger = LogManager.getLogger(HttpTaskRangeWalkerServiceImpl.class);

    @Autowired
    @Qualifier("JDBCMemberWriterServiceImpl")
    private MemberWriterService memberWriterService;

    @Autowired
    private DynamicDictionaryService dynamicDictionaryService;

    @Autowired
    @Qualifier("JDBCTaskProviderImpl")
    public TaskProvider taskProvider;

    @Autowired
    MemberInfoService memberInfoService;

    private List<Integer> vips = new ArrayList<>();

    private Random random = new Random();

    public void processSeries(Iterable<Integer> mids) throws SQLException {
        Iterator<Integer> i = mids.iterator();
        while (i.hasNext()) {
            Optional<MemberInfo> memberInfo = memberInfoService.getMemberInfo(i.next());
            if (memberInfo.isPresent()) {
                memberWriterService.upsert(memberInfo.get());
                logger.info(":: upsert {}", memberInfo);
            }
            SqlRuUtils.sleep(5 + random.nextInt(2));
        }
    }



    @Override
    public void walk() {
        logger.info(":: begin walk");
        try {
            walkVIPs(vips);
        } catch (SQLException ex) {
            logger.error(":: fatal error ", ex);
        }
    }

    private void walkVIPs(@NotNull List<Integer> asList) throws SQLException {
        logger.info(":: walk VIP-s");
        ThreadContext.put("taskId", "VIP");
        processSeries(asList);
        ThreadContext.remove("taskId");
        logger.info(":: end of walk VIP-s");
    }

    public List<Integer> getVips() {
        return vips;
    }

    public void setVips(List<Integer> vips) {
        this.vips = vips;
    }
}
