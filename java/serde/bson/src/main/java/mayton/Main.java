package mayton;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static Options createOptions() {
        return new Options()
                .addRequiredOption("s", "source", true, "Source file")
                .addOption("d", "destination", true, "Destination file");
    }

    public static void main(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = createOptions();
        if (args.length == 0) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar ApplicationName-1.0.jar", createOptions(), true);
        } else {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("java -jar ApplicationName-1.0.jar", createOptions(), true);
                return;
            }
            process(line);
        }
    }

    private static void process(CommandLine line) {
        logger.info("Start");
        
        logger.info("Finish");
    }
}
