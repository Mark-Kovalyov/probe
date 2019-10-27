package mayton.semanticweb;

import mayton.lib.SofarTracker;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
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

    public static Map<IRI, Pair<String, String>> processTRDDLCreator(InputStream inputStream, long statements, RDFFormat rdfFormat, String namespace) throws Exception {

        logger.info(":: process DDL for table");

        RDFParser rdfParser = Rio.createParser(rdfFormat);

        SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("statements", statements);

        logger.info("{}", sofarTracker.toString());

        AnalyzerHandler trddl = new AnalyzerHandler(sofarTracker);

        rdfParser.setRDFHandler(trddl);

        new Thread(new SofarWatchDog(sofarTracker)).start();

        rdfParser.parse(inputStream, namespace);

        logger.info(":: finish processing DDL");

        return trddl.getPredicates();

    }

    public static long processDatabaseLoader(InputStream inputStream, long statements, RDFFormat rdfFormat, Map<IRI, Pair<String, String>> predicates, String namespace) throws Exception {

        logger.info(":: start processing database loading");

        TRDatabaseSQLWriterHandler handler = new TRDatabaseSQLWriterHandler(predicates, new PrintWriter(("sql/out.sql")));

        RDFParser rdfParser = Rio.createParser(rdfFormat);

        rdfParser.setRDFHandler(handler);

        rdfParser.parse(inputStream, namespace);

        return 0L;
    }


    public static void processFile(String path, String namespace) throws Exception {

        InputStream inputStream = new FileInputStream(path);

        long statements = statements(inputStream, RDFFormat.TURTLE, namespace);

        inputStream = new FileInputStream(path);

        Map<IRI, Pair<String, String>> predicates = processTRDDLCreator(inputStream, statements, RDFFormat.TURTLE, namespace);

        inputStream = new FileInputStream(path);

        processDatabaseLoader(inputStream, statements, RDFFormat.TURTLE, predicates, namespace);

    }



    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("sensitive.properties"));

        String path = properties.getProperty("source");
        String namespace = properties.getProperty("namespace");

        processFile(path, namespace);

    }

}
