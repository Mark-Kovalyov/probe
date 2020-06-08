package mayton.html;

import java.sql.Connection;
import java.util.Optional;

public interface ConnectionPoolComponent {

    Optional<Connection> createConnection();

}
