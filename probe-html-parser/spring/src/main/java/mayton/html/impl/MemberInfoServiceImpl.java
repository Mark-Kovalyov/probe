package mayton.html.impl;

import mayton.html.entities.MemberInfo;
import mayton.html.MemberInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberInfoServiceImpl implements MemberInfoService {

    static Logger logger = LogManager.getLogger(MemberInfoServiceImpl.class);

    @Override
    public Optional<MemberInfo> getMemberInfo(int id) {
        return Optional.empty();
    }

}
