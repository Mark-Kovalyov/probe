package mayton.probe.ignite.logging;

import org.jetbrains.annotations.NotNull;

public class DfLoggerFactory {

    public static DfLogger createLogger(@NotNull Class clazz) {
        return new DfLogger(clazz.getName());
    }

    public static DfLogger createLogger(@NotNull String className) {
        return new DfLogger(className);
    }

}
