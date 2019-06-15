package mayton.probe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProbeSyslog {

    static Logger logger = LogManager.getLogger(ProbeSyslog.class);

    public static void main(String[] args) throws InterruptedException {

        logger.info("Hello from Log4j2! ");

        Thread.sleep(20 * 1000);

        for (int i = 0; i < 1500; i++) {
            logger.info("Hello from Log4j2! Count = {}", i);
            Thread.sleep(1 * 1000);
        }

        logger.info("Waiting for 15 sec");

        Thread.sleep(15 * 1000);

    }
}
