package mayton.html;

import mayton.html.entities.TaskInfo;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Optional;

public interface TaskProvider {

    Optional<TaskInfo> provideNextTask() throws SQLException;

    void updateTaskStatus(@NotNull TaskInfo taskState, TaskState newTaskState) throws SQLException;

}
