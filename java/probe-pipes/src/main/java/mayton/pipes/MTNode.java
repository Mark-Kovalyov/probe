package mayton.pipes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public abstract class MTNode implements AutoCloseable {

    protected Thread thread;

    protected Logger logger;

    protected PipedInputStream pipedInputStream;
    protected PipedOutputStream pipedOutputStream;

    public MTNode(String threadName) {
        this.logger = LoggerFactory.getLogger(threadName);
    }

    public abstract void run() throws IOException ;

    @Override
    public void close() throws Exception {
        pipedInputStream.close();
        pipedOutputStream.close();
    }
}
