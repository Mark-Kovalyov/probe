package mayton.probe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class ProbeSyslog {

    static Logger logger = LogManager.getLogger(ProbeSyslog.class);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            logger.info("Hello from Log4j2! Count = {}", i);
            Thread.sleep(1 * 1000);
        }

        Thread.sleep(30 * 1000);

    }
}
