package mayton.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

@WebServlet(asyncSupported = true)
public class AsyncLongRunningServlet extends HttpServlet {

    static Logger logger = LoggerFactory.getLogger(AsyncLongRunningServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        logger.info("AsyncLongRunningServlet Start::Name={} ::ID={}", Thread.currentThread().getName(), Thread.currentThread().getId());

        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

        String time = request.getParameter("time");
        long milliseconds = 1000 * Long.valueOf(time);
        // max 10 seconds

        milliseconds = Math.min(milliseconds, 10000);

        // (1) startAsync()
        AsyncContext asyncContext = request.startAsync();
        asyncContext.addListener(new AppAsyncListener());
        asyncContext.setTimeout(9 * 1000L);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");

        executor.execute(new AsyncRequestProcessor(asyncContext, milliseconds));

        long endTime = System.currentTimeMillis();
        logger.info("LongRunningServlet Start::Name={} ::ID={} ::Time Taken={} ms",
                Thread.currentThread().getName()
                , Thread.currentThread().getId(), (endTime - startTime));

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info(":: init");
        super.init(config);
    }
}
