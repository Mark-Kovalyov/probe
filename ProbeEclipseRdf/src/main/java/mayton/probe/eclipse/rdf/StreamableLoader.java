package mayton.probe.eclipse.rdf;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.TxnType;
import org.apache.jena.tdb.TDB;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.tdb2.TDB2;
import org.apache.jena.tdb2.TDB2Factory;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.rio.ParserConfig;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.ContextStatementCollector;
import org.eclipse.rdf4j.rio.helpers.ParseErrorLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import javax.management.MXBean;
import javax.management.ObjectName;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.zip.GZIPInputStream;

import static mayton.probe.eclipse.rdf.Constants.*;

public class StreamableLoader  {

    static Logger logger = LoggerFactory.getLogger(StreamableLoader.class);

    public static void main(String[] args) throws Exception {

        logger.info("::[1]");

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        InputStream in = new GZIPInputStream(new FileInputStream(SRC_PATH + QUOTES_GZIP));

        ParserConfig settings = new ParserConfig();

        RDFFormat dataFormat = RDFFormat.TURTLE;

        ValueFactory valueFactory = SimpleValueFactory.getInstance();

        RDFParser parser = Rio.createParser(dataFormat, valueFactory);

        parser.setParserConfig(settings);
        parser.setParseErrorListener(new ParseErrorLogger());

        Resource[] resources = new Resource[0];

        //parser.setRDFHandler(new ContextStatementCollector(model, valueFactory, resources));

        StreamStatementHandler handler = new StreamStatementHandler();
        mbs.registerMBean(handler,
                new ObjectName("mayton.probe.eclipse.rdf:name=StreamableLoader"));

        logger.info("::[3]");

        Dataset dataset = TDB2Factory.connectDataset(TDB2_PATH);

        logger.info("::[4]");

        dataset.begin(TxnType.WRITE);

        handler.setDataset(dataset);

        parser.setRDFHandler(handler);

        logger.info("::[5]");

        parser.parse(in, "");

        logger.info("::[6]");

        ReportHelper.printMap("Subjects   map:", handler.subjMap);
        ReportHelper.printMap("Predicates map:", handler.predMap);
        ReportHelper.printMap("Object     map:", handler.objMap);

        dataset.commit();
        dataset.close();

    }

}
