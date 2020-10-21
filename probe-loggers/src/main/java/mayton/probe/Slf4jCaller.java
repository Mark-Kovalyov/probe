package mayton.probe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jCaller {

    public static Logger logger = LoggerFactory.getLogger(Slf4jCaller.class);

    public static void info(String message) {
        logger.info(message);
    }



}
