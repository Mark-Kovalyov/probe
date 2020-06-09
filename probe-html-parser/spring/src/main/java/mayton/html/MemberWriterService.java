package mayton.html;

import mayton.html.entities.MemberInfo;
import org.jetbrains.annotations.NotNull;

public interface MemberWriterService {

    void upsert(@NotNull MemberInfo memberInfo);

}
