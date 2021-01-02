package mayton.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet //(value = "/longrun")
public class LongRunningServlet extends HttpServlet {

    static Logger logger = LoggerFactory.getLogger(LongRunningServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        logger.info("LongRunningServlet Start::Name={} ::ID={}", Thread.currentThread().getName() , Thread.currentThread().getId());

        String time = request.getParameter("time");
        long secs = 1000L * Long.valueOf(time);

        // max 10 seconds

        secs = Math.min(secs, 10000);

        longProcessing(secs);

        PrintWriter out = response.getWriter();
        long endTime = System.currentTimeMillis();
        out.write("Processing done for " + secs + " milliseconds!!");

        logger.info("LongRunningServlet Start::Name={} ::ID={} ::Time Taken={} ms",
                Thread.currentThread().getName(),
                Thread.currentThread().getId(),
                (endTime - startTime));

    }

    private void longProcessing(long ms) {
        // wait for given time before finishing
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("",e);
        }
    }
}
