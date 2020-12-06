package mayton.html.impl;

import com.google.common.util.concurrent.RateLimiter;
import mayton.html.HtmlParserException;
import mayton.html.MemberInfoService;
import mayton.html.MemberWriterService;
import mayton.html.WalkerService;
import mayton.html.entities.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Component("random")
public class HttpRandomWalkerServiceImpl implements WalkerService {

    @Autowired
    MemberInfoService memberInfoService;

    @Autowired
    @Qualifier("JDBCMemberWriterServiceImpl")
    MemberWriterService memberWriterService;

    private RateLimiter rateLimiter = null;

    private AtomicBoolean isWorking = new AtomicBoolean(true);

    @Value("${walker.rateLimiterParameter}")
    private double rateLimiterParameter;

    @PostConstruct
    public void postConstruct() {
        rateLimiter = RateLimiter.create(rateLimiterParameter);
    }

    @Override
    public void walk() {
        try {
            Random random = new Random();
            while (isWorking.get()) {
                rateLimiter.acquire();
                Optional<MemberInfo> memberInfo = memberInfoService.getMemberInfo(125_000 + random.nextInt(125_000));
                if (memberInfo.isPresent()) {
                    memberWriterService.upsert(memberInfo.get());
                }
                //SqlRuUtils.sleep(10 + random.nextInt(5));
            }
        } catch(SQLException ex) {
            throw new HtmlParserException(ex);
        }
    }

}
