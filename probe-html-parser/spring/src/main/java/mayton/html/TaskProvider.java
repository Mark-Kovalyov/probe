package mayton.html;

import mayton.html.entities.TaskInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface TaskProvider {

    Optional<TaskInfo> provideNextTask();

    void updateTaskStatus(@NotNull TaskInfo taskState, TaskState newTaskState);

}
