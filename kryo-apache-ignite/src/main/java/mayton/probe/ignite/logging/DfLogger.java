package mayton.probe.ignite.logging;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteLogger;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class DfLogger {

    @IgniteInstanceResource
    private Ignite ignite;

    private IgniteLogger igniteLogger;

    private String className;

    public DfLogger(String className) {
        this.className = className;
        IgniteConfiguration cfg = ignite.configuration();

        //URL xml = U.resolveIgniteUrl("config/custom-log4j.xml");
        //IgniteLogger log = new Log4JLogger(xml);
        //cfg.setGridLogger(log);
    }

    public void debug(String message) {
        if (igniteLogger.isDebugEnabled()) {
            igniteLogger.debug(message);
        }
    }

    public void debug(Supplier<String> messageSupplier) {
        if (igniteLogger.isDebugEnabled()) {
            igniteLogger.debug(messageSupplier.get());
        }
    }


    public void info(String message) {
        if (igniteLogger.isInfoEnabled()) {
            igniteLogger.info(message);
        }
    }

    public void info(Supplier<String> messageSupplier) {
        if (igniteLogger.isInfoEnabled()) {
            igniteLogger.info(messageSupplier.get());
        }
    }


    public void warn(String message) {
        igniteLogger.warning(message);
    }

    public void warn(Supplier<String> messageSupplier) {
        igniteLogger.warning(messageSupplier.get());
    }

    public void warn(Supplier<String> messageSupplier, @Nullable Throwable throwable) {
        igniteLogger.warning(messageSupplier.get(), throwable);
    }


    public void error(String message) {
        igniteLogger.error(message);
    }

    public void error(Supplier<String> messageSupplier) {
        igniteLogger.error(messageSupplier.get());
    }

    public void error(Supplier<String> messageSupplier, @Nullable Throwable throwable) {
        igniteLogger.error(messageSupplier.get(), throwable);
    }


}
