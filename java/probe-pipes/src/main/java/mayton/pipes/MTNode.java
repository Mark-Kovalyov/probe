package mayton.pipes;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.function.BiConsumer;

public class MTNode {

    private PipedInputStream pipedInputStream;
    private PipedOutputStream pipedOutputStream;
    private Thread thread;

    public MTNode(BiConsumer<PipedInputStream, PipedOutputStream> biConsumer) {
        thread = new Thread(() -> biConsumer.accept(pipedInputStream, pipedOutputStream));
    }

    public void start() {
        thread.start();
    }

    public void join() throws InterruptedException {
        thread.join();
    }
}
