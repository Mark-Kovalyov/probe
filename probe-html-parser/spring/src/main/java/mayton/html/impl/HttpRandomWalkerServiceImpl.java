package mayton.html.impl;

import mayton.html.HtmlParserException;
import mayton.html.MemberInfoService;
import mayton.html.MemberWriterService;
import mayton.html.WalkerService;
import mayton.html.entities.MemberInfo;
import mayton.html.utils.SqlRuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;

@Component("random")
public class HttpRandomWalkerServiceImpl implements WalkerService {

    @Autowired
    MemberInfoService memberInfoService;

    @Autowired
    MemberWriterService memberWriterService;

    private Random random = new Random();

    @Override
    public void walk() {
        try {
            while (true) {
                Optional<MemberInfo> memberInfo = memberInfoService.getMemberInfo(125_000 + random.nextInt(125_000));
                if (memberInfo.isPresent()) {
                    memberWriterService.upsert(memberInfo.get());
                }
                SqlRuUtils.sleep(10 + random.nextInt(5));
            }
        } catch(SQLException ex) {
            throw new HtmlParserException(ex);
        }
    }

}
