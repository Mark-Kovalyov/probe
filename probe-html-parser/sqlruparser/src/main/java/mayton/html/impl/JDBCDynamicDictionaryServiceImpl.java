package mayton.html.impl;

import mayton.html.ConnectionPoolComponent;
import mayton.html.DynamicDictionaryService;
import mayton.html.utils.PGUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.concurrent.ThreadSafe;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
@Component
public class JDBCDynamicDictionaryServiceImpl implements DynamicDictionaryService {

    static Logger logger = LogManager.getLogger(JDBCDynamicDictionaryServiceImpl.class);

    private Map<String, Integer> dictionaryCache = new HashMap<>();

    @Autowired
    public ConnectionPoolComponent connectionPoolComponent;

    @PostConstruct
    public void warmUpCache() throws SQLException {
        logger.info("Warm up cache...");
        Connection conn = connectionPoolComponent.createConnection();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT key, value FROM dictionary")) {
            while (resultSet.next()) {
                int key = resultSet.getInt(1);
                String value = resultSet.getString(2);
                logger.trace(":: initialized with dict pair[{}] = {}", key, value);
                dictionaryCache.put(value, key);
            }
            logger.info("Warm up cache finished, {} keys added", dictionaryCache.size());
        } catch (SQLException ex) {
            logger.warn("", ex);
            throw new SQLException(ex);
        } finally {
            PGUtils.safeClose(conn);
        }
    }

    @Override
    public synchronized int getOrCreateEntityId(@NotNull String entityName) throws SQLException {
        if (dictionaryCache.containsKey(entityName)) {
            return dictionaryCache.get(entityName);
        } else {
            Connection conn = connectionPoolComponent.createConnection();
            try (PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO dictionary(value) VALUES(?) " +
                    "ON CONFLICT(value) DO NOTHING " +
                    "RETURNING key", Statement.RETURN_GENERATED_KEYS)) {

                conn.setAutoCommit(false);
                preparedStatement.setObject(1, entityName);
                preparedStatement.execute();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                rs.next();
                int key = rs.getInt(1);
                rs.close();
                conn.commit();
                dictionaryCache.put(entityName, key);
                logger.info(":: new dynamic dict pair [{}] = {}", entityName, key);
                return key;
            } catch (SQLException ex) {
                logger.error("", ex);
                throw new SQLException(ex);
            } finally {
                PGUtils.safeClose(conn);
            }
        }

    }
}
