package mayton.pipes;

import mayton.pipes.nodes.CSVParserProtoWriterNode;
import mayton.pipes.nodes.GZipDecoderNode;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class MTPipeLineStarter {

    public static Logger logger = LoggerFactory.getLogger(MTPipeLineStarter.class);

    public static Options createOptions() {
        return new Options()
                .addRequiredOption("s", "source", true, "Source file")
                .addOption("d", "destination", true, "Destination file");
    }

    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
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

    private static void process(CommandLine line) throws IOException, InterruptedException {
        logger.info("Start");

        MTPipeline mtPipeline = new MTPipeline(new FileInputStream(Constants.SRC));
        mtPipeline.addNode(new GZipDecoderNode("gzipdecoder"));
        mtPipeline.addNode(new CSVParserProtoWriterNode("csvparser"));
        mtPipeline.addTerminalNode(new FileOutputStream(Constants.OUT));

        logger.info("Finish");
    }
}
