package mayton.html;

import mayton.html.entities.TaskInfo;

import java.util.Optional;

public interface TaskProvider {

    Optional<TaskInfo> provideNextTask();

}
