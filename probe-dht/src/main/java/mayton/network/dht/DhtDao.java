package mayton.network.dht;

import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DhtDao {

    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:postgres");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addDhtPeer() {
        try {
            Statement statement = connection.createStatement();
            //statement.executeUpdate("insert into peers(ip) values('127.0.0.1')");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
