package mayton.html;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface ConnectionPoolComponent {

    Connection createConnection() throws SQLException;

}
