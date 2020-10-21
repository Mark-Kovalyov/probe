package mayton.probe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Caller {
    static Logger logger = LogManager.getLogger(Log4j2Caller.class);

    public static void info(String message) {
        logger.info(message);
    }
}
