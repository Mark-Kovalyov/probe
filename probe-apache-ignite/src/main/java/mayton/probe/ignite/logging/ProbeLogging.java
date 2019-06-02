package mayton.probe.ignite.logging;

public class ProbeLogging {

    public static void main(String[] args) {

        DfLogger logger = DfLoggerFactory.createLogger(ProbeLogging.class);

        DfLogger logger2 = DfLoggerFactory.createLogger("mayton.CustomNameLogger");




    }

}
