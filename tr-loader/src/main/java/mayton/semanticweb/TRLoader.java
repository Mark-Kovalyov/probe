package mayton.semanticweb;

import mayton.lib.SofarTracker;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;


public class TRLoader {

    static Logger logger = LoggerFactory.getLogger(TRLoader.class);

    public static long countStatements(InputStream inputStream, RDFFormat rdfFormat, String baseUri) throws IOException {
        logger.info(":: Estimation");
        /*RDFParser rdfParser = Rio.createParser(rdfFormat);
        CountingHandler countingHandler = new CountingHandler();
        rdfParser.setRDFHandler(countingHandler);
        rdfParser.parse(inputStream, baseUri);
        return countingHandler.getStatements();*/
        logger.info(":: Finish estimation");
        return 42_520_959;
    }

    public static Map<IRI, Pair<String, String>> ddlAndPredicates(InputStream inputStream, long statements, RDFFormat rdfFormat, String namespace) throws Exception {

        logger.info(":: process DDL for table");

        RDFParser rdfParser = Rio.createParser(rdfFormat);

        SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("statements", statements);

        logger.info("{}", sofarTracker.toString());

        AnalyzerHandler analyzerHandler = new AnalyzerHandler();
        analyzerHandler.bind(sofarTracker);

        rdfParser.setRDFHandler(analyzerHandler);

        new Thread(new SofarWatchDog(sofarTracker)).start();

        rdfParser.parse(inputStream, namespace);

        logger.info(":: finish processing DDL");

        return analyzerHandler.getPredicates();

    }

    public static long load(@Nonnull InputStream inputStream, long statements, RDFFormat rdfFormat, Map<IRI, Pair<String, String>> predicates, String namespace, PrintWriter pw) throws Exception {

        logger.info(":: start loading");

        TRDatabaseSQLWriterHandler handler = new TRDatabaseSQLWriterHandler(predicates, pw);
        SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("statements", statements);
        handler.bind(sofarTracker);
        RDFParser rdfParser = Rio.createParser(rdfFormat);
        rdfParser.setRDFHandler(handler);
        new Thread(new SofarWatchDog(sofarTracker)).start();

        rdfParser.parse(inputStream, namespace);

        logger.info(":: finish loading");

        // TODO add row counter
        return 0L;
    }


    public static void processFile(String path, String namespace, RDFFormat rdfFormat, PrintWriter printWriter) throws Exception {
        MDC.put("mode", "Count");
        // Count statements
        long statements = countStatements(new FileInputStream(path), rdfFormat, namespace);

        MDC.put("mode", "DDL and stats");
        // Process generate table DDL & gather stats
        Map<IRI, Pair<String, String>> predicates = ddlAndPredicates(new FileInputStream(path), statements, rdfFormat, namespace);

        MDC.put("mode", "Generate SQL");
        // Load
        long dataRows = load(new FileInputStream(path), statements, rdfFormat, predicates, namespace, printWriter);
        logger.info(":: generated dataRows = {}", dataRows);
        MDC.remove("mode");
    }

    // postgres=# create database tr;
    // postgres=# GRANT ALL PRIVILEGES ON DATABASE tr TO mayton;
    //
    // $

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("sensitive.properties"));
        String source = properties.getProperty("source");
        String namespace = properties.getProperty("namespace");
        String dest = properties.getProperty("dest");
        logger.info("source = {}", source);
        logger.info("dest   = {}", dest);
        logger.info("namespace = {}", namespace);
        new File(dest.substring(0, dest.lastIndexOf('/'))).mkdirs();
        PrintWriter printWriter = new PrintWriter(dest);
        processFile(source, namespace, RDFFormat.TURTLE, printWriter);
        printWriter.flush();
        logger.info(":: psql -d myDataBase -a -f '{}'", source);
        printWriter.close();
    }

}
