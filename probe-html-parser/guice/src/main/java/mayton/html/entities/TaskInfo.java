package mayton.html.entities;

import mayton.html.TaskState;

import javax.annotation.concurrent.Immutable;

/**
 * create table tasks(
 *     id INT GENERATED ALWAYS AS IDENTITY,
 *     member_start int not null,
 *     member_end int not null,
 *     state varchar(30) -- ready, inprocess, finished
 * );
 */
@Immutable
public final class TaskInfo {

    private final int id;
    private final int memberStart;
    private final int memberEnd;
    private final TaskState taskState;

    public TaskInfo(int id, int memberStart, int memberEnd, TaskState taskState) {
        this.id = id;
        this.memberStart = memberStart;
        this.memberEnd = memberEnd;
        this.taskState = taskState;
    }

    public int getId() {
        return id;
    }

    public int getMemberStart() {
        return memberStart;
    }

    public int getMemberEnd() {
        return memberEnd;
    }

    public TaskState getTaskState() {
        return taskState;
    }
}
