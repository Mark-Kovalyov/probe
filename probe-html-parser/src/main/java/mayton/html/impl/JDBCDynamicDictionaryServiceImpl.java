package mayton.html.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import mayton.html.ConnectionPoolComponent;
import mayton.html.DynamicDictionaryService;
import mayton.html.utils.PGUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.ThreadSafe;
import java.sql.*;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.StampedLock;

@Singleton
@ThreadSafe
public class JDBCDynamicDictionaryServiceImpl implements DynamicDictionaryService {

    static Logger logger = LogManager.getLogger(JDBCDynamicDictionaryServiceImpl.class);

    private ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap();

    private StampedLock stampedLock = new StampedLock();

    @Inject
    private ConnectionPoolComponent connectionPoolComponent;

    public void warmUpCache() {
        logger.info("Warm up cache...");
        Optional<Connection> connectionOptional = connectionPoolComponent.createConnection();
        if (connectionOptional.isPresent()) {
            Connection conn = connectionOptional.get();
            try(Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT key,value FROM dictionary");
                while (resultSet.next()) {
                    int key = resultSet.getInt(1);
                    String value = resultSet.getString(2);
                    logger.trace(":: dict[{}] = {}", key, value);
                    concurrentHashMap.put(value, key);
                }
                logger.info("Warm up cache finished, {} keys added", concurrentHashMap.size());
            } catch (SQLException ex) {
                logger.warn("", ex);
            } finally {
                PGUtils.safeClose(conn);
            }
        } else {
            logger.error("Fatal! Unable to get access to db!");
            throw new RuntimeException("Fatal! Unable to get access to db!");
        }
    }

    @Inject
    public JDBCDynamicDictionaryServiceImpl() {
        warmUpCache();
    }

    @Override
    public int getOrCreateEntityId(@NotNull String entityName) {
        if (concurrentHashMap.contains(entityName)) {
            return concurrentHashMap.get(entityName);
        } else {
            Optional<Connection> connectionOptional = connectionPoolComponent.createConnection();
            if (connectionOptional.isPresent()) {
                Connection conn = connectionOptional.get();
                try(PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO dictionary(value) VALUES(?) RETURNING key")) {
                    conn.setAutoCommit(false);
                    preparedStatement.setObject(1, entityName);
                    preparedStatement.execute();
                    conn.commit();
                } catch (SQLException ex) {
                    logger.info("", ex);
                }
                PGUtils.safeClose(conn);
            }
        }
        return 0;
    }
}
