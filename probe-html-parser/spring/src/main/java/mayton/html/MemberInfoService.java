package mayton.html;

import mayton.html.entities.MemberInfo;

import java.sql.SQLException;
import java.util.Optional;

public interface MemberInfoService {

    Optional<MemberInfo> getMemberInfo(int id) throws SQLException;

}
