package mayton.cache.jcs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    static Logger logger = LogManager.getLogger("Main");

    public static boolean doIt() {
        logger.traceEntry();
        logger.trace(":: Trace");
        logger.info(":: Info");
        logger.warn(":: Warn");
        logger.error(":: Error");
        logger.fatal(":: Fatal");
        return logger.traceExit(false);
    }

    public static void main(String[] args) {
        boolean res = doIt();
    }
}
