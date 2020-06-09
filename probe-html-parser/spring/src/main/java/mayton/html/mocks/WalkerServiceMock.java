package mayton.html.mocks;

import mayton.html.*;
import mayton.html.entities.MemberInfo;
import mayton.html.entities.TaskInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public class WalkerServiceMock implements WalkerService {

    static Logger logger = LogManager.getLogger(WalkerServiceMock.class);

    @Autowired
    private ConnectionPoolComponent connectionPoolComponent;

    @Autowired
    @Qualifier("mock")
    private TaskProvider taskProvider;

    @Autowired
    private MemberWriterService memberWriterService;

    @Autowired
    @Qualifier("mock")
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
                memberWriterService.upsert(info);
            }
        }
    }
}
