package mayton.probe;

import java.util.logging.Logger;

public class JULCaller {

    public static void info(String message) {
        Logger logger = Logger.getLogger(JULCaller.class.getName());
        logger.info(message);
    }

}
