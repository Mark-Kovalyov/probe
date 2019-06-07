package mayton.probe.ignite.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

    public static void main(String[] args) throws Exception {
        System.out.println("java.version = " + System.getProperty("java.version"));

        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");

        System.out.println("::1");

        Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1:10800");

        System.out.println("::2");

        conn.close();

        System.out.println("::OK");
    }
}
