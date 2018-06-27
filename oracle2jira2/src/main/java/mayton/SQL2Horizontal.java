package mayton;

import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import static mayton.Console.printf;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQL2Horizontal {
    
    
    
    static Logger logger = LoggerFactory.getLogger(SQL2Vertical.class);

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO: Wtf?
        //Class.forName("oracle.jdbc.OraceDriver");        
        boolean hideNulls = true;
        logger.info("::1");
        logger.info("user.dir = {}", System.getProperty("user.dir"));
        String url = args[0];  // args[0];
        if (url.equals("-")) {
            url = "scott/tiger@127.0.0.1:1521/XE";
        }
        String sql = args[1];
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:" + url);
        Properties props = conn.getClientInfo();
        Statement st = conn.createStatement();        
        Properties formProps = new Properties();
        IFormatter jiraHorFormatter = new JiraHorizontalFormatter(formProps);        
        jiraHorFormatter.processResultSet(System.out, st.executeQuery(sql));        
        st.close();        
        conn.close();
        logger.info("OK");
    }
}
