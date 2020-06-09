package mayton.html.impl;

import mayton.html.TaskProvider;
import mayton.html.TaskState;
import mayton.html.entities.TaskInfo;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JDBCTaskProviderImpl implements TaskProvider {

    @Override
    public Optional<TaskInfo> provideNextTask() {
        // TODO: Implement
        return Optional.empty();
    }

    @Override
    public void updateTaskStatus(@NotNull TaskInfo taskInfo, TaskState newTaskState) {

    }


}
