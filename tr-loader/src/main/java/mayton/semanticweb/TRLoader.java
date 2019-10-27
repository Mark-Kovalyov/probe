package mayton.semanticweb;

import mayton.lib.SofarTracker;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;


public class TRLoader {

    static Logger logger = LoggerFactory.getLogger(TRLoader.class);

    public static long statements(InputStream inputStream, RDFFormat rdfFormat, String baseUri) throws IOException {
        logger.info(":: Estimation");
        /*RDFParser rdfParser = Rio.createParser(rdfFormat);
        CountingHandler countingHandler = new CountingHandler();
        rdfParser.setRDFHandler(countingHandler);
        rdfParser.parse(inputStream, baseUri);
        return countingHandler.getStatements();*/
        logger.info(":: Finish estimation");
        return 42_520_959;
    }

    public static Map<IRI, String> processTRDDLCreator(InputStream inputStream, long statements, RDFFormat rdfFormat) throws Exception {

        logger.info(":: process DDL for table");

        RDFParser rdfParser = Rio.createParser(rdfFormat);

        final SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("statements", statements);

        logger.info("{}", sofarTracker.toString());

        TRDDLCreator trddl = new TRDDLCreator(sofarTracker);

        rdfParser.setRDFHandler(trddl);

        new Thread(() -> {
            try {
                while (true) {
                    synchronized (sofarTracker) {
                        logger.info(sofarTracker.toString());
                        if (sofarTracker.getPosition() == sofarTracker.getSize()) {
                            logger.info("Sofat tracker watcher is finished!");
                            break;
                        }
                    }
                    Thread.sleep(3000);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                logger.warn("Interrupted");
            } catch (Exception ex) {
                logger.error(ex.toString());
            }
        }).start();

        rdfParser.parse(inputStream, "http://permid.org");

        logger.info(":: finish processing DDL");

        return trddl.getPredicates();

    }

    public static long processDatabaseLoader(InputStream inputStream, long statements, RDFFormat rdfFormat, Map<IRI, String> predicates) throws Exception {
        logger.info(":: start processing database loading");

        return 0L;
    }

    public static void processFile(String path, String namespace) throws Exception {

        InputStream inputStream = new FileInputStream(path);

        long statements = statements(inputStream, RDFFormat.TURTLE, namespace);

        inputStream = new FileInputStream(path);

        Map<IRI, String> predicates = processTRDDLCreator(inputStream, statements, RDFFormat.TURTLE);

        inputStream = new FileInputStream(path);

        processDatabaseLoader(inputStream, statements, RDFFormat.TURTLE, predicates);

    }

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("sensitive.properties"));

        String path = properties.getProperty("source");
        String namespace = properties.getProperty("namespace");

        processFile(path, namespace);

    }

}
