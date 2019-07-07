package mayton.jdbc;

import java.sql.*;
import java.util.logging.Logger;

public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {

        logger.info(":: Begin java.version = " + System.getProperty("java.version"));

        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
        Class.forName("mayton.jdbc.TnDriver");
        Class.forName("org.postgresql.Driver");

        logger.info(":: Driver registered");

        //Connection igniteConnection = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1:10800");

        Connection tnConnection = DriverManager.getConnection("jdbc:tn://localhost:10800");

        DatabaseMetaData metadata = new TnMetadata();

        ResultSet tables = metadata.getTables("", "", "", new String[]{});


        Statement statement = tnConnection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM BITEMPORAL");

        while(resultSet.next()) {
            long begin = resultSet.getLong(1);
            long end = resultSet.getLong(2);
            long key = resultSet.getLong(3);
            double value = resultSet.getDouble(4);
        }

        resultSet.close();

        statement.close();

        logger.info(":: Connection taken");

        tnConnection.close();
        //igniteConnection.close();

        logger.info(":: End");

    }

}
