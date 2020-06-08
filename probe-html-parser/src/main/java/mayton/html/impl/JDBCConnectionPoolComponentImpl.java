package mayton.html.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import mayton.html.Config;
import mayton.html.ConnectionPoolComponent;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.System.setProperty;

@Singleton
public class JDBCConnectionPoolComponentImpl implements ConnectionPoolComponent {

    static Logger logger = LogManager.getLogger(JDBCConnectionPoolComponentImpl.class);

    private String initialContextFactory;

    @Inject
    private Config config;

    @PostConstruct
    public void init() {
        logger.info("init PgConnectionPoolComponent");

        checkNotNull(config);
        LinkedHashMap<String, Object> dbcpConfig = (LinkedHashMap<String, Object>) config.getRoot().get("dbcp");
        initialContextFactory = (String) dbcpConfig.get("INITIAL_CONTEXT_FACTORY");
        logger.trace("INITIAL_CONTEXT_FACTORY = {}", initialContextFactory);
        setProperty(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
        setProperty(Context.PROVIDER_URL, (String) dbcpConfig.get("PROVIDER_URL"));

        InitialContext initialContext ;
        try {
            initialContext = new InitialContext();
            LinkedHashMap<String, Object> databaseConfig = (LinkedHashMap<String, Object>) config.getRoot().get("database");
            // Construct BasicDataSource
            BasicDataSource bds = new BasicDataSource();
            bds.setDriverClassName((String) databaseConfig.get("driverClassName"));
            bds.setUrl((String) databaseConfig.get("url"));
            bds.setUsername((String) databaseConfig.get("username"));
            bds.setPassword((String) databaseConfig.get("password"));

            initialContext.rebind("jdbc/parserdb", bds);
            logger.info("init PgConnectionPoolComponent complete!");
        } catch (NamingException e) {
            logger.warn("Unable to init PgConnectionPoolComponent", e);
        }
    }

    @Override
    public Optional<Connection> createConnection() {
        InitialContext initialContext;
        try {
            initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("jdbc/parserdb");
            return Optional.of(ds.getConnection());
        } catch (NamingException | SQLException e) {
            logger.warn("Unable to create connection", e);
            return Optional.empty();
        }
    }

}
