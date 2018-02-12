/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mayton;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import static mayton.Console.printf;

public class JiraHorizontalFormatter implements IFormatter {
    
    static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static String DATE_FORMAT = "yyyy-MM-dd";

    private Properties props;

    public JiraHorizontalFormatter(Properties formProps) {
        this.props = props;
    }

    public void processResultSet(PrintStream ps, ResultSet rs) throws SQLException {
        String nullReplacement = "[null]";
        SimpleDateFormat sdtf = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat sdf  = new SimpleDateFormat(DATE_FORMAT);
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        for (int i = 1; i < cols; i++) {
            ps.printf("||%s", rsmd.getColumnName(i));
        }
        ps.printf("||\n");
        while (rs.next()) {
            for (int i = 1; i < cols; i++) {
                Object value = rs.getString(i);
                int type = rs.getType();
                switch(type) {
                    case Types.VARCHAR :
                        ps.printf("|%s", value);
                    case Types.CHAR :
                        ps.printf("|%s", value);
                    case Types.DATE :
                        Date dt = (Date)value;
                        ps.printf("|%s", sdtf.format(dt));
                    default:
                        ps.printf("|%s", value);
                }
            }
            ps.printf("|\n");
        }
        rs.close();
    }

}
