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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@Singleton
public class DbMemberWriterServiceImpl implements MemberWriterService {

    static Logger logger = LogManager.getLogger(DbMemberWriterServiceImpl.class);

    @Inject
    ConnectionPoolComponent connectionPoolComponent;

    /**
     * create table member_info(
     *     id int primary key,
     *     messsages int not null,
     *     nickname varchar(255) not null,
     *     hist jsonb
     * );
     * @param memberInfo
     */
    @Override
    public void write(@NotNull MemberInfo memberInfo) {
        Optional<Connection> optionalConnection = connectionPoolComponent.createConnection();
        if (optionalConnection.isPresent()) {
            try {
                PreparedStatement statement = optionalConnection.get().prepareStatement(
                        "insert into member_info(id,messages,nickname,hist) values (?, ?, ?, ?)"
                );
                statement.setInt(1, memberInfo.getId());
                statement.setInt(2, memberInfo.getMessages());
                statement.setString(3, memberInfo.getNickname());
                statement.setString(4, PGUtils.mapToJson(memberInfo.getMessagesDistibution()));
                statement.executeUpdate();
                statement.close();
            } catch (SQLException ex) {
                logger.error("", ex);
            }
        } else {
            logger.warn("Unable to get connection from pool");
        }
    }
}
