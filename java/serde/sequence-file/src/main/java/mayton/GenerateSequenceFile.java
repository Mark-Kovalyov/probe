package mayton;

import org.apache.commons.cli.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;

import java.io.IOException;

public class GenerateSequenceFile {

    public static Logger logger = LoggerFactory.getLogger(GenerateSequenceFile.class);

    public static Options createOptions() {
        return new Options()
                .addRequiredOption("s", "source", true, "Source file")
                .addOption("d", "destination", true, "Destination file");
    }

    public static void main(String[] args) throws ParseException, IOException, InstantiationException, IllegalAccessException {
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

    private static boolean isPrime(int i) {
        if (i % 2 == 0 || i % 3 == 0) return false;
        boolean res = false;
        for(int k = 3; k < 1 + (int) Math.sqrt(i); k += 2) {
            if (i % k == 0) {
                return false;
            }
        }
        return true;
    }

    private static void process(CommandLine line) throws IOException, InstantiationException, IllegalAccessException {


        logger.info("Start");

        String pathString = "data/file-01.seq";

        Configuration readConfig = new Configuration();

        SequenceFile.Writer.Option file = SequenceFile.Writer.file(new Path(pathString));
        SequenceFile.Writer.Option compr = SequenceFile.Writer.compression(SequenceFile.CompressionType.NONE);
        SequenceFile.Writer.Option keyClass = SequenceFile.Writer.keyClass(VIntWritable.class);
        SequenceFile.Writer.Option valueClass = SequenceFile.Writer.valueClass(VLongWritable.class);
        SequenceFile.Writer.Option bs = SequenceFile.Writer.blockSize(4096);
        SequenceFile.Writer.Option append = SequenceFile.Writer.appendIfExists(false);
        SequenceFile.Writer.Option interv = SequenceFile.Writer.syncInterval(4096);

        SequenceFile.Writer writer = SequenceFile.createWriter(readConfig, file, compr, keyClass, valueClass, bs, append, interv);

        VIntWritable key = new VIntWritable();
        VLongWritable value = new VLongWritable();
        int cnt = 1;
        long maxPrime = 0;
        Profiler profiler = new Profiler("Generate sequence of " + VIntWritable.class + " and " + VLongWritable.class);
        for (int i = 0; i < 1000; i++) {
            if (isPrime(i)) {
                key.set(cnt);
                value.set(i);
                writer.append(key, value);
                cnt++;
                maxPrime = i;
            }
        }
        writer.hflush();
        writer.close();

        profiler.start("Reading sequence");

        // new SequenceFile.Reader(FileSystem.get(config), path, config);
        // SequenceFile.Reader reader = new SequenceFile.Reader(FileSystem.get(readConfig), new Path(pathString), readConfig);

        Configuration writeConfig = new Configuration();

        SequenceFile.Reader reader = new SequenceFile.Reader(readConfig);

        WritableComparable readKey = (WritableComparable) reader.getKeyClass().newInstance();
        Writable readValue = (Writable) reader.getValueClass().newInstance();

        while (reader.next(key, value)) {

        }
        reader.close();

        profiler.stop().print();

        logger.info("Detected {} primes, maxPrime = {}", cnt, maxPrime);



        logger.info("Finish");
    }
}
