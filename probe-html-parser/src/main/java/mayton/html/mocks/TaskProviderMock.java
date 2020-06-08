package mayton.html.mocks;

import mayton.html.TaskProvider;
import mayton.html.TaskState;
import mayton.html.entities.TaskInfo;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class TaskProviderMock implements TaskProvider {

    private Lock lock = new ReentrantLock();

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public Optional<TaskInfo> provideNextTask() {
        // int id, int memberStart, int memberEnd, TaskState taskState
        try {
            lock.lock();
            if (atomicInteger.get() > 250_000) {
                return Optional.empty();
            } else {
                atomicInteger.addAndGet(1000);
                return Optional.of(
                        new TaskInfo(0,
                                atomicInteger.get(),
                                atomicInteger.get() + 100,
                                TaskState.READY));
            }
        } finally {
            lock.unlock();
        }
    }
}
