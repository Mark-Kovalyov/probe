package mayton;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IFormatter {
    
    void processResultSet(PrintStream ps, ResultSet rs) throws SQLException;
    
}
