package mayton.html.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import mayton.html.ConnectionPoolComponent;
import mayton.html.entities.MemberInfo;
import mayton.html.MemberWriterService;
import mayton.html.utils.PGUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@Singleton
public class JDBCMemberWriterServiceImpl implements MemberWriterService {

    static Logger logger = LogManager.getLogger(JDBCMemberWriterServiceImpl.class);

    @Inject
    ConnectionPoolComponent connectionPoolComponent;

    @Override
    public void upsert(@NotNull MemberInfo memberInfo) {
        Optional<Connection> optionalConnection = connectionPoolComponent.createConnection();
        if (optionalConnection.isPresent()) {
            Connection conn = optionalConnection.get();
            try(PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO member_info(id,messages,nickname,hist) VALUES (?, ?, ?, ?) " +
                        "ON CONFLICT (id) " +
                        "DO UPDATE SET messages = ?, hist = ?"
                )) {
                conn.setAutoCommit(false);
                // INSERT
                statement.setInt(1, memberInfo.getId());
                statement.setInt(2, memberInfo.getMessages());
                statement.setString(3, memberInfo.getNickname());
                PGobject jsonObject = new PGobject();
                jsonObject.setType("json");
                String histJson = PGUtils.mapToJson(memberInfo.getMessagesDistibution());
                logger.trace(histJson);
                jsonObject.setValue(histJson);
                statement.setObject(4, jsonObject);
                // UPDATE
                statement.setInt(5, memberInfo.getMessages());
                statement.setObject(6, jsonObject);
                statement.executeUpdate();
                conn.commit();
            } catch (SQLException ex) {
                logger.error("[1]", ex);
            } finally {
                PGUtils.safeClose(conn);
            }
        } else {
            logger.warn("Unable to get connection from pool");
        }
    }
}
