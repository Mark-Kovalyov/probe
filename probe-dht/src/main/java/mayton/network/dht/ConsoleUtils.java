package mayton.network.dht;

import java.io.PrintStream;

public class ConsoleUtils {

    public static PrintStream printf(String format, Object ...args) {
        return System.out.printf(format, args);
    }

    public static void println(String arg) {
        System.out.println(arg);
    }

}
