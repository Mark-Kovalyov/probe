package mayton.html.impl;

import mayton.html.ConnectionPoolComponent;
import mayton.html.TaskProvider;
import mayton.html.TaskState;
import mayton.html.entities.TaskInfo;
import mayton.html.utils.PGUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Optional;

@Component("JDBCTaskProviderImpl")
public class JDBCTaskProviderImpl implements TaskProvider {

    static Logger logger = LogManager.getLogger(JDBCTaskProviderImpl.class);

    @Autowired
    private ConnectionPoolComponent connectionPoolComponent;

    @Override
    public Optional<TaskInfo> provideNextTask() throws SQLException {
        Connection connection = connectionPoolComponent.createConnection();
        try {
            Optional<TaskInfo> result = Optional.empty();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, member_start, member_end, state, last_update_time " +
                            "FROM tasks " +
                            "WHERE state in ('READY')");
            if (resultSet.next()) {
                result = Optional.of(new TaskInfo(
                        resultSet.getInt("id"),
                        resultSet.getInt("member_start"),
                        resultSet.getInt("member_end"),
                        TaskState.valueOf(resultSet.getString("state"))));
            }
            statement.close();
            return result;
        } catch (SQLException ex) {
            logger.error("", ex);
            throw new RuntimeException("Unable provideNexTask!");
        } finally {
            PGUtils.safeClose(connection);
        }

    }

    @Override
    public void updateTaskStatus(@NotNull TaskInfo taskInfo, TaskState newTaskState) throws SQLException {
        Connection connection = connectionPoolComponent.createConnection();
        try {
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tasks SET state = ? WHERE id = ?");
            preparedStatement.setString(1, newTaskState.name());
            preparedStatement.setInt(2, taskInfo.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.warn("", ex);
        } finally {
            PGUtils.safeClose(connection);
        }
    }


}
