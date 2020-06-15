package mayton.html.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import mayton.html.Config;
import mayton.html.ConnectionPoolComponent;
import mayton.html.HtmlParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Component
public class JDBCConnectionPoolComponentImpl implements ConnectionPoolComponent {

    static Logger logger = LogManager.getLogger(JDBCConnectionPoolComponentImpl.class);

    private HikariConfig hikariConfig = new HikariConfig();

    private HikariDataSource ds;

    @Autowired
    public Config config;

    private String lookupSensitiveProperty(String propertyName) {
        Map<String, Object> hikariConfigMap = (Map<String, Object>) config.getRoot().get("hikariConfig");
        if (hikariConfigMap.containsKey(propertyName)) {
            return (String) hikariConfigMap.get(propertyName);
        } else if (System.getProperties().containsKey(propertyName)) {
            return System.getProperty(propertyName);
        } else if (System.getenv().containsKey(propertyName)) {
            return System.getenv().get(propertyName);
        } else {
            throw new HtmlParserException("Unable to found property " + propertyName);
        }
    }

    @PostConstruct
    public void init() {
        logger.info(":: init for JDBCConnectionPoolComponentImpl");
        Map<String, Object> hikariConfigMap = (Map<String, Object>) config.getRoot().get("hikariConfig");

        hikariConfig.setDriverClassName(lookupSensitiveProperty("driverClassName"));
        hikariConfig.setJdbcUrl(lookupSensitiveProperty("jdbcUrl"));
        hikariConfig.setUsername(lookupSensitiveProperty("username"));
        hikariConfig.setPassword(lookupSensitiveProperty("password"));

        hikariConfig.addDataSourceProperty("minimumIdle", hikariConfigMap.get("minimumIdle"));
        hikariConfig.addDataSourceProperty("maximumPoolSize", hikariConfigMap.get("maximumPoolSize"));
        hikariConfig.addDataSourceProperty("idleTimeout", hikariConfigMap.get("idleTimeout"));
        hikariConfig.addDataSourceProperty("maxLifetime", hikariConfigMap.get("maxLifetime"));
        hikariConfig.addDataSourceProperty("connectionTimeout", hikariConfigMap.get("connectionTimeout"));
        hikariConfig.addDataSourceProperty("cachePrepStmts", hikariConfigMap.get("cachePrepStmts"));
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", hikariConfigMap.get("prepStmtCacheSize"));
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", hikariConfigMap.get("prepStmtCacheSqlLimit"));
        ds = new HikariDataSource(hikariConfig);
    }

    @Override
    public Connection createConnection() throws SQLException {
        return ds.getConnection();
    }

}
