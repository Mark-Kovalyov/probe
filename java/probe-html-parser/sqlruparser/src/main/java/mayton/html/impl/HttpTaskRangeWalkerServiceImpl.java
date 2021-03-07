package mayton.html.impl;

import com.google.common.util.concurrent.RateLimiter;
import mayton.html.*;
import mayton.html.entities.MemberInfo;
import mayton.html.entities.TaskInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static mayton.html.TaskState.*;

/**
 * Route's mebers by id ranges
 */
@SuppressWarnings("java:S1192")
@Component("rangewalker")
@ConfigurationProperties(prefix = "walker.rangewalker")
public class HttpTaskRangeWalkerServiceImpl implements WalkerService {

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

    private RateLimiter rateLimiter = null;

    private double rateLimiterParameter;

    public void processSeries(Iterable<Integer> mids) throws SQLException {
        Iterator<Integer> i = mids.iterator();
        while (i.hasNext()) {
            rateLimiter.acquire();
            Optional<MemberInfo> memberInfo = memberInfoService.getMemberInfo(i.next());
            if (memberInfo.isPresent()) {
                memberWriterService.upsert(memberInfo.get());
                logger.info(":: upsert {}", memberInfo);
            }
        }
    }

    @Override
    public void walk() {
        logger.info(":: begin walk");
        try {
            rateLimiter = RateLimiter.create(rateLimiterParameter);
            walkRegularMembers();
        } catch (SQLException ex) {
            logger.error(":: fatal error ", ex);
        }
    }

    private void walkRegularMembers() throws SQLException {
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
                processSeries(midsLinearSequence);
                taskProvider.updateTaskStatus(taskInfo, FINISHED);
                ThreadContext.remove("taskId");
            }
        }
        logger.info(":: end of walk regular members");
    }

    public double getRateLimiterParameter() {
        return rateLimiterParameter;
    }

    public void setRateLimiterParameter(double rateLimiterParameter) {
        this.rateLimiterParameter = rateLimiterParameter;
    }
}
