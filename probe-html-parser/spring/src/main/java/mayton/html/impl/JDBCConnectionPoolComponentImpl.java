package mayton.html.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import mayton.html.Config;
import mayton.html.ConnectionPoolComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@Component
public class JDBCConnectionPoolComponentImpl implements ConnectionPoolComponent {

    static Logger logger = LogManager.getLogger(JDBCConnectionPoolComponentImpl.class);

    private HikariConfig hikariConfig = new HikariConfig();
    private HikariDataSource ds;

    @Autowired
    private Config config;

    public void prepareHikariConfig() {


        Map<String, Object> hikariConfigMap = (Map<String, Object>) config.getRoot().get("hikariConfig");

        hikariConfig.setDriverClassName((String) hikariConfigMap.get("driverClassName"));
        hikariConfig.setJdbcUrl((String) hikariConfigMap.get("jdbcUrl"));
        hikariConfig.setUsername((String) hikariConfigMap.get("username"));
        hikariConfig.setPassword((String) hikariConfigMap.get("password"));

        // TODO: Use the config
        hikariConfig.addDataSourceProperty("minimumIdle", "5");
        hikariConfig.addDataSourceProperty("maximumPoolSize", "20");
        hikariConfig.addDataSourceProperty("idleTimeout", "30000");
        hikariConfig.addDataSourceProperty("maxLifetime", "2000000");
        hikariConfig.addDataSourceProperty("connectionTimeout", "30000");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "4");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "4");
        ds = new HikariDataSource(hikariConfig);
    }

    @Autowired
    public void JDBCConnectionPoolComponentImpl() {
        prepareHikariConfig();
    }

    @Override
    public Optional<Connection> createConnection() {
        try {
            return Optional.of(ds.getConnection());
        } catch (SQLException ex) {
            logger.warn("Unable to create connection from pool " + ds.toString(), ex);
            return Optional.empty();
        }
    }

}
