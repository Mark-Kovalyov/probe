package mayton.html.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import mayton.html.ConnectionPoolComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@ConfigurationProperties(prefix = "hikariconfig")
public class JDBCConnectionPoolComponentImpl implements ConnectionPoolComponent {

    static Logger logger = LogManager.getLogger(JDBCConnectionPoolComponentImpl.class);

    private HikariConfig hikariConfig = new HikariConfig();

    private HikariDataSource ds;

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;

    @PostConstruct
    public void init() {
        logger.info(":: init for JDBCConnectionPoolComponentImpl");

        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);

        hikariConfig.addDataSourceProperty("minimumIdle", 5);
        hikariConfig.addDataSourceProperty("maximumPoolSize", 20);
        hikariConfig.addDataSourceProperty("idleTimeout", 30000);
        hikariConfig.addDataSourceProperty("maxLifetime", 2000000);
        hikariConfig.addDataSourceProperty("connectionTimeout", 30000);
        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 4);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 4);

        ds = new HikariDataSource(hikariConfig);
    }

    @Override
    public Connection createConnection() throws SQLException {
        return ds.getConnection();
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
