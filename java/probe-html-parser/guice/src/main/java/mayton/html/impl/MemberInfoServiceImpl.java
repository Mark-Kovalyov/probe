package mayton.html.impl;

import com.google.inject.Singleton;
import mayton.html.entities.MemberInfo;
import mayton.html.MemberInfoService;
import org.jetbrains.annotations.NotNull;

@Singleton
public class MemberInfoServiceImpl implements MemberInfoService {

    @Override
    public MemberInfo getMemberInfo(int id) {
        throw new RuntimeException("Non implemented!");
    }

}
