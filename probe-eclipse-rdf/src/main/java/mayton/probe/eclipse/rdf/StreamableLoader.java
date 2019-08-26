package mayton.probe.eclipse.rdf;

import mayton.lib.SofarTracker;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.TxnType;
import org.apache.jena.tdb2.TDB2Factory;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.rio.ParserConfig;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.ParseErrorLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.Properties;
import java.util.concurrent.locks.LockSupport;
import java.util.zip.GZIPInputStream;

public class StreamableLoader  {

    static Logger logger = LoggerFactory.getLogger("StreamableLoader");

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.load(new FileInputStream("sensitive.properties"));

        String inputFile = props.getProperty("inputFile");
        String dataSetPath = props.getProperty("dataSetPath");

        logger.info("::[1] input file = '{}', dataSetPath = '{}'", inputFile, dataSetPath);

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        InputStream in;

        long amount;

        if (inputFile.endsWith(".gz")) {
            // TODO: Remove hardcode!
            amount = 794_872_433;
            in = new GZIPInputStream(new FileInputStream(inputFile));
            logger.info("::[1.1] procesing Gzip stream");
        } else {
            amount = new File(inputFile).length();
            in = new FileInputStream(inputFile);
            logger.info("::[1.1] procesing Raw stream");
        }


        try(CountingInputStream cis = new CountingInputStream(in)) {

            new Thread(() -> {
                SofarTracker sofarTracker = SofarTracker.createFileSizeTracker(amount);
                while (true) {
                    sofarTracker.update(cis.getByteCount());
                    logger.info(sofarTracker.toString());
                    try {
                        LockSupport.parkNanos(3 * 1000_000_000L);
                    } catch (Exception ex) {
                        logger.warn(ex.toString());
                    }
                }
            }).start();

            ParserConfig settings = new ParserConfig();

            RDFFormat dataFormat = RDFFormat.TURTLE;

            ValueFactory valueFactory = SimpleValueFactory.getInstance();

            RDFParser parser = Rio.createParser(dataFormat, valueFactory);

            parser.setParserConfig(settings);
            parser.setParseErrorListener(new ParseErrorLogger());

            StreamStatementHandler handler = new StreamStatementHandler();

            mbs.registerMBean(handler,
                    new ObjectName("mayton.probe.eclipse.rdf:name=StreamableLoader"));

            logger.info("::[3]");

            Dataset dataset = TDB2Factory.connectDataset(dataSetPath);


            logger.info("::[4]");

            dataset.begin(TxnType.WRITE);

            handler.setDataset(dataset);

            parser.setRDFHandler(handler);

            logger.info("::[5]");

            parser.parse(cis, "");

            logger.info("::[6]");

            ReportHelper.printMap("Subjects   map:", handler.subjMap);
            ReportHelper.printMap("Predicates map:", handler.predMap);
            ReportHelper.printMap("Object     map:", handler.objMap);

            dataset.commit();
            dataset.close();
        }

    }

}
