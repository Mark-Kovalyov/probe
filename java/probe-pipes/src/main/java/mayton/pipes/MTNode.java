package mayton.pipes;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class MTNode {

    private PipedInputStream pipedInputStream;
    private PipedOutputStream pipedOutputStream;
    private Thread thread;

    public MTNode(Runnable runnable) {
        thread = new Thread(runnable);
    }

    public void start() {
        thread.start();
    }

    public void join() throws InterruptedException {
        thread.join();
    }
}
