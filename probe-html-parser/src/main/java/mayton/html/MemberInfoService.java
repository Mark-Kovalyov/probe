package mayton.html;

import mayton.html.entities.MemberInfo;
import org.jetbrains.annotations.NotNull;

public interface MemberInfoService {

    @NotNull MemberInfo getMemberInfo(int id);

}
