package mayton.html.impl;

import mayton.html.ConnectionPoolComponent;
import mayton.html.entities.MemberInfo;
import mayton.html.MemberWriterService;
import mayton.html.utils.PGUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class JDBCMemberWriterServiceImpl implements MemberWriterService {

    static Logger logger = LogManager.getLogger(JDBCMemberWriterServiceImpl.class);

    @Autowired
    ConnectionPoolComponent connectionPoolComponent;

    @Override
    public void upsert(@NotNull MemberInfo memberInfo) throws SQLException {
        Connection conn = connectionPoolComponent.createConnection();

        try (PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO member_info2(id,messages,nickname,hist,state,email) VALUES (?, ?, ?, ?, ?, ?) " +
                        "ON CONFLICT (id) " +
                        "DO UPDATE SET messages = ?, hist = ?, state = ?, nickname = ?, email = ?"
        )) {
            conn.setAutoCommit(false);
            int cnt = 1;
            // ------------------- INSERT ------------------------
                statement.setInt(cnt++, memberInfo.getId());
                statement.setInt(cnt++, memberInfo.getMessages());
                statement.setString(cnt++, memberInfo.getNickname());
                PGobject jsonObject = new PGobject();
                jsonObject.setType("json");
                String histJson = PGUtils.mapToJson(memberInfo.getMessagesDistibution());
                logger.trace(histJson);
                // TODO: Try with jsonb_object(...) string SQL function
                jsonObject.setValue(histJson);
                statement.setObject(cnt++, jsonObject);
                statement.setString(cnt++, memberInfo.getState());
                statement.setString(cnt++, memberInfo.getEmail());
            // ---------------- UPDATE --------------------------
                statement.setInt(cnt++, memberInfo.getMessages());
                // TODO: Try with jsonb_object(...) string SQL function
                statement.setObject(cnt++, jsonObject);
                statement.setString(cnt++, memberInfo.getState());
                statement.setString(cnt++, memberInfo.getNickname());
                statement.setString(cnt, memberInfo.getEmail());
                statement.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            logger.error("[1]", ex);
        } finally {
            PGUtils.safeClose(conn);
        }

    }
}
