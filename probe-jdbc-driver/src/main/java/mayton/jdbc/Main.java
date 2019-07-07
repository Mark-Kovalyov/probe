package mayton.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {

        logger.info(":: Begin java.version = " + System.getProperty("java.version"));

        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
        Class.forName("mayton.jdbc.TnDriver");
        Class.forName("org.postgresql.Driver");

        logger.info(":: Driver registered");

        Connection igniteConnection = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1:10800");

        Connection tnConnection = DriverManager.getConnection("jdbc:tn://localhost:11211");

        logger.info(":: Connection taken");

        tnConnection.close();
        igniteConnection.close();

        logger.info(":: End");

    }

}
