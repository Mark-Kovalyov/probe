package mayton.html;

import mayton.html.entities.MemberInfo;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public interface MemberWriterService {

    void upsert(@NotNull MemberInfo memberInfo) throws SQLException;

}
