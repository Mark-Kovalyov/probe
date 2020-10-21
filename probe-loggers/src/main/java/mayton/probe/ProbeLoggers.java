package mayton.probe;

public class ProbeLoggers {

    public static void main(String[] args) {
        System.setProperty("log4j2.debug", "true");

        JULCaller.info("Hello from JUL (Java.util.Logger)");
        Slf4jCaller.info("Hello from SLF4j");
        JCLCaller.info("Hello from JCL (Java Commons Logging)");
        Log4j2Caller.info("Hello from Log4j-2");

    }

}
