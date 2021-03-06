package mayton.semanticweb;

import mayton.lib.SofarTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SofarWatchDog implements Runnable {

    static Logger logger = LoggerFactory.getLogger(SofarWatchDog.class);

    private SofarTracker sofarTracker;

    public SofarWatchDog(SofarTracker sofarTracker) {
        this.sofarTracker = sofarTracker;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // TODO: Replace with Atomic
                synchronized (sofarTracker) {
                    logger.info(sofarTracker.toString());
                    if (sofarTracker.getPosition() == sofarTracker.getSize()) {
                        logger.info("Sofar tracker watcher is finished!");
                        break;
                    }
                }
                Thread.sleep(15 * 1000L);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            logger.warn("Interrupted");
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
    }
}
