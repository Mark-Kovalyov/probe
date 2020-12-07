package mayton.html.impl;

import com.google.common.util.concurrent.RateLimiter;
import mayton.html.HtmlParserException;
import mayton.html.MemberInfoService;
import mayton.html.MemberWriterService;
import mayton.html.WalkerService;
import mayton.html.entities.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Component("randomwalker")
@ConfigurationProperties(prefix = "randomwalker.rangewalker")
public class HttpRandomWalkerServiceImpl implements WalkerService {

    @Autowired
    MemberInfoService memberInfoService;

    @Autowired
    @Qualifier("JDBCMemberWriterServiceImpl")
    MemberWriterService memberWriterService;

    private RateLimiter rateLimiter = null;

    private AtomicBoolean isWorking = new AtomicBoolean(true);

    private double rateLimiterParameter;

    @Override
    public void walk() {
        try {
            rateLimiter = RateLimiter.create(rateLimiterParameter);
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

    public RateLimiter getRateLimiter() {
        return rateLimiter;
    }

    public void setRateLimiter(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }
}
