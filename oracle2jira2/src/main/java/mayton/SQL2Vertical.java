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
import static mayton.Console.println;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQL2Vertical {
    
    static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    static Logger logger = LoggerFactory.getLogger(SQL2Vertical.class);

    public static void dumpPropertiesSorted(Properties p) {
        Set<String> set = new TreeSet();
        Enumeration<Object> elements = p.elements();
        while (elements.hasMoreElements()) {
            set.add((String) elements.nextElement());
        }
        for (String name : set) {
            logger.info("{} = '{}'", name, p.getProperty(name));
        }
    }

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
        dumpPropertiesSorted(props);
        logger.info("::2");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        while (rs.next()) {
            printf("||Name||Value||\n");
            for (int i = 1; i < cols; i++) {
                String name = rsmd.getColumnName(i);
                String value = rs.getString(i);
                if (hideNulls) {
                    if (value != null) {
                        printf("|%s|%s|\n", name, value);
                    }
                } else {
                    printf("|%s|%s|\n", name, value);
                }
            }
            println();
        }
        rs.close();
        st.close();
        conn.close();
        logger.info("OK");
    }
}
