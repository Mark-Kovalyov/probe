package mayton.html.impl;

import mayton.html.entities.MemberInfo;
import mayton.html.MemberInfoService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberInfoServiceImpl implements MemberInfoService {

    @Override
    public Optional<MemberInfo> getMemberInfo(int id) {
        return Optional.empty();
    }

}
