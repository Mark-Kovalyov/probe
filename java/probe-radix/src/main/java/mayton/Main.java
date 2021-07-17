package mayton;

import com.google.common.collect.Iterators;
import com.googlecode.concurrenttrees.radix.ConcurrentRadixTree;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;
import mayton.lib.SofarTracker;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.TimeInstrument;

import java.io.*;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

public class Main {

    //public static final String SRC = "/storage/db/1.2billion/1.2billion.txt.gz";

    public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static Options createOptions() {
        return new Options()
                .addRequiredOption("s", "source", true, "Source file")
                .addOption("a", "stopafter", true, "Stop after");
    }

    public static void main(String[] args) throws ParseException, IOException {
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

    private static void process(CommandLine line) throws IOException {
        logger.info("Start");
        Profiler profiler = new Profiler("Radix loader");
        profiler.setLogger(logger);
        profiler.start("prepare loading");
        GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(line.getOptionValue("s")));
        BufferedReader br = new BufferedReader(new InputStreamReader(gzipInputStream, "utf-8"));
        String buf = null;
        DefaultCharArrayNodeFactory dcf = new DefaultCharArrayNodeFactory();
        ConcurrentRadixTree crt = new ConcurrentRadixTree(dcf);
        SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("line", 1_200_000_000);
        int lines = 0;
        int linesFiltered = 0;
        int stopAfter = Integer.MAX_VALUE;
        if (line.hasOption("stopafter")) {
            stopAfter = Integer.parseInt(line.getOptionValue("stopafter"));
        }

        profiler.start("loading");
        sofarTracker.update(0);
        logger.info(sofarTracker.toString());
        do {
            buf = br.readLine();
            if (buf.length() < 80 && StringUtils.isAlphanumeric(buf)) {
                crt.put(buf, "");
                linesFiltered++;
                if ((lines & 0xFFFF) == 0xFFFF) {
                    sofarTracker.update(lines);
                    logger.info(sofarTracker.toString());
                }
            }
            lines++;
        } while(buf != null && lines < stopAfter);
        sofarTracker.finish();
        logger.info("lines = {}", lines);
        logger.info("lines filtered = {}", linesFiltered);
        logger.info(sofarTracker.toString());
        profiler.start("counting by prefix 'Password'");
        Iterator res = crt.getKeysStartingWith("mayton").iterator();
        while(res.hasNext()) {
            logger.info(":: {}",res.next());
        }
        TimeInstrument report = profiler.stop();
        report.log();
        logger.info("Finish");

    }
}
