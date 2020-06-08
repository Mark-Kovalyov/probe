package mayton.html.mocks;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import mayton.html.*;
import mayton.html.entities.MemberInfo;
import mayton.html.entities.TaskInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@Singleton
public class WalkerServiceMock implements WalkerService {

    static Logger logger = LogManager.getLogger(WalkerServiceMock.class);

    @Inject
    private ConnectionPoolComponent connectionPoolComponent;

    @Inject
    private TaskProvider taskProvider;

    @Inject
    private MemberWriterService memberWriterService;

    @Inject
    private MemberInfoService memberInfoService;

    @Override
    public void walk(@NotNull String url) {
        // for all tasks
        //    for all memberIds
        //        download Member Info

        Optional<TaskInfo> taskInfoOptional;

        while((taskInfoOptional = taskProvider.provideNextTask()).isPresent()) {
            TaskInfo taskInfo = taskInfoOptional.get();
            for(int i = taskInfo.getMemberStart(); i<taskInfo.getMemberEnd(); i++) {
                MemberInfo info = memberInfoService.getMemberInfo(i);
                memberWriterService.write(info);
            }
        }
    }
}
