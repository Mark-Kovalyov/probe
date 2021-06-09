package mayton.pipes;

import mayton.geo.GeoIpEntity;
import mayton.network.NetworkUtils;
import org.apache.commons.cli.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.NullInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.zip.GZIPInputStream;

public class Main {

    public static Logger logger = LoggerFactory.getLogger(Main.class);

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

        NullInputStream nullInputStream = new NullInputStream();

        MTChain mtChain = new MTChain(new GZIPInputStream(new FileInputStream(line.getOptionValue("s"))));

        mtChain.addNode((pipedInputStream, pipedOutputStream) -> {
            try {
                int res = IOUtils.copy(pipedInputStream, pipedOutputStream);
                pipedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        mtChain.addNode((pipedInputStream, pipedOutputStream) -> {
            CSVParser parser = null;
            try {
                parser = CSVParser.parse(pipedInputStream, StandardCharsets.UTF_8,
                        CSVFormat.DEFAULT
                                .withDelimiter(',')
                                .withFirstRecordAsHeader());


                Iterator<CSVRecord> iterator = parser.iterator();
                int cnt = 0;
                while(iterator.hasNext()) {
                    CSVRecord csvRecord = iterator.next();
                    GeoIpEntity.GeoIpCity geoIpEntity = GeoIpEntity.GeoIpCity.newBuilder()
                            .setStartIpNum((int) NetworkUtils.parseIpV4(csvRecord.get(0)))
                            .setEndIpNum((int) NetworkUtils.parseIpV4(csvRecord.get(1)))
                            .setCountry(csvRecord.get(2))
                            .setRegion(csvRecord.get(3))
                            .setCity(csvRecord.get(4))
                            .setLatitude(0.0)
                            .setLongitude(0.0)
                            .build();
                    geoIpEntity.writeDelimitedTo(pipedOutputStream);
                    cnt++;
                }
                pipedOutputStream.close();
            logger.info("[2] copied {} records" , cnt);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        mtChain.startNodes();

        mtChain.joinNodes();

        logger.info("Finish");
    }
}
