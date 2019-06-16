package mayton.probe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.HttpAppender;
import org.apache.logging.log4j.core.appender.SmtpAppender;

public class ProbeSyslog {

    static Logger logger = LogManager.getLogger(ProbeSyslog.class);

    //
    public static void main(String[] args) throws InterruptedException {

        logger.info("Waiting for 5 sec first");

        Thread.sleep(5 * 1000);

        for (int i = 0; i < 15; i++) {
            logger.info("Hello from Log4j2! Count = {}", i);
            Thread.sleep(1000);
        }

        logger.info("Waiting for 5 sec second");

        Thread.sleep(5 * 1000);

        ConsoleAppender

    }
}
