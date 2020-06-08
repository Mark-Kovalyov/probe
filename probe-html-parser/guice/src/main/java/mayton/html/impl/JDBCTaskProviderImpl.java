package mayton.html.impl;

import com.google.inject.Singleton;
import mayton.html.TaskProvider;
import mayton.html.entities.TaskInfo;

import java.util.Optional;

@Singleton
public class JDBCTaskProviderImpl implements TaskProvider {
    @Override
    public Optional<TaskInfo> provideNextTask() {
        // TODO: Implement
        return Optional.empty();
    }
}
