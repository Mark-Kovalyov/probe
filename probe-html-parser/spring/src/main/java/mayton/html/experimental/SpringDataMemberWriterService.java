package mayton.html.experimental;

import mayton.html.MemberWriterService;
import mayton.html.entities.MemberInfo;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component("experimental")
public class SpringDataMemberWriterService implements MemberWriterService {


    @Override
    public void upsert(@NotNull MemberInfo memberInfo) throws SQLException {

    }
}
