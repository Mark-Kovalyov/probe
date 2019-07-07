package mayton.jdbc;

import org.apache.ignite.IgniteJdbcThinDriver;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.quote;

public class TnDriver implements Driver {

    static Logger logger = Logger.getLogger(TnDriver.class.getName());

    private static final Driver INSTANCE = new TnDriver();

    private static volatile boolean registered;

    static {
        register();
    }

    public static final String PORT_PATTERN = "(\\:(?<port>\\d{1,5}))?";

    public static final String DOMAIN_HOST_PATTERN = "(?<domainname>[a-z0-9\\.]+)";

    //public static final String IP_HOST_PATTERN = "(?<ip>\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";

    public static final String TN_DRIVER_URL_PATTERN = "^" + quote("jdbc:tn://") + "" +
                                                                    DOMAIN_HOST_PATTERN + "" +
                                                                    PORT_PATTERN;

    @Override
    public Connection connect(String url, Properties info) throws SQLException {

        // jdbc:postgresql://[::1]:5740/accounting
        // jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true
        // jdbc:tn://localhost:11211



        Pattern pattern = Pattern.compile(TN_DRIVER_URL_PATTERN, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(url);

        if (matcher.matches()) {
            String host = matcher.group("domainname");
            String port = matcher.group("port");

            return new TnConnection(host, Integer.parseInt(port));
        }

        return null;
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return false;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    public static synchronized Driver register() {
        try {
            if (!registered) {
                DriverManager.registerDriver(INSTANCE);
                registered = true;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Failed to register Tn JDBC thin driver.", e);
        }

        return INSTANCE;
    }
}
