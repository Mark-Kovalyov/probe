package mayton.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AppAsyncListener implements AsyncListener {

    static Logger logger = LoggerFactory.getLogger(AppAsyncListener.class);

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        logger.info(":: on Complete");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        logger.info(":: on timeout");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        logger.info(":: on error");
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        logger.info(":: on StartAsync");
        ServletResponse response = asyncEvent.getAsyncContext().getResponse();
        PrintWriter out = response.getWriter();
        out.write("TimeOut Error in Processing");
    }
}
