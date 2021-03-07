package mayton.probe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JCLCaller {
    static Log log = LogFactory.getLog(JCLCaller.class);

    public static void info(String message) {
        log.info(message);
    }
}
