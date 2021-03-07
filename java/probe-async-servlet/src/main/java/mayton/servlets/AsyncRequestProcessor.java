package mayton.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;

public class AsyncRequestProcessor implements Runnable {

    static Logger logger = LoggerFactory.getLogger(AsyncRequestProcessor.class);

    private AsyncContext asyncContext;
    private long milliseconds;

    public AsyncRequestProcessor(AsyncContext asyncContext, long milliseconds) {
        this.asyncContext = asyncContext;
        this.milliseconds = milliseconds;
    }

    @Override
    public void run() {
        logger.info("Async Supported? {}", asyncContext.getRequest().isAsyncSupported());
        longProcessing(milliseconds);
        try {
            PrintWriter out = asyncContext.getResponse().getWriter();
            out.write("Processing done for " + milliseconds + " milliseconds!!");
        } catch (IOException e) {
            logger.error("",e);
        }
        // (2) complete the processing
        asyncContext.complete();
    }

    private void longProcessing(long milliseconds) {
        // wait for given time before finishing
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
