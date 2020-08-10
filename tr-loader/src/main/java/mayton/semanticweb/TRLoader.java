package mayton.semanticweb;

import mayton.lib.SofarTracker;
import mayton.semanticweb.rdfhandlers.TRDatabaseCSVWriterHandler;
import mayton.semanticweb.rdfhandlers.TRDatabaseDDLAnalyzer;
import mayton.semanticweb.rdfhandlers.TRDatabaseMultilineSQLWriterHandler;
import mayton.semanticweb.rdfhandlers.TRDatabaseSQLWriterHandler;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.zip.GZIPInputStream;


public class TRLoader {

    static Logger logger = LoggerFactory.getLogger(TRLoader.class);

    public static long countStatements(InputStream inputStream, RDFFormat rdfFormat, String baseUri) throws IOException {
        logger.info(":: Estimation");
        RDFParser rdfParser = Rio.createParser(rdfFormat);
        CountingHandler countingHandler = new CountingHandler();
        rdfParser.setRDFHandler(countingHandler);
        rdfParser.parse(inputStream, baseUri);
        logger.info(":: Finish estimation");
        return countingHandler.getStatements();
    }

    public static Map<IRI, Pair<String, String>> ddlAndPredicates(InputStream inputStream, long statements, RDFFormat rdfFormat, String namespace, String tableName) throws Exception {

        logger.info(":: process DDL for table");

        RDFParser rdfParser = Rio.createParser(rdfFormat);

        SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("statements", statements);

        logger.info("{}", sofarTracker.toString());

        TRDatabaseDDLAnalyzer TRDatabaseDDLAnalyzer = new TRDatabaseDDLAnalyzer(tableName);
        TRDatabaseDDLAnalyzer.bind(sofarTracker);

        rdfParser.setRDFHandler(TRDatabaseDDLAnalyzer);

        new Thread(new SofarWatchDog(sofarTracker)).start();

        rdfParser.parse(inputStream, namespace);

        logger.info(":: finish processing DDL");

        Map<IRI, Pair<String, String>> res = TRDatabaseDDLAnalyzer.getPredicates();

        TRDatabaseDDLAnalyzer.close();

        return res;
    }

    public static long load(@Nonnull InputStream inputStream, long statements, RDFFormat rdfFormat, Map<IRI, Pair<String, String>> predicates, String namespace, PrintWriter pw, String tableName, int batchSize) throws Exception {

        logger.info(":: start loading");

        TRDatabaseSQLWriterHandler handler = new TRDatabaseSQLWriterHandler(predicates, pw, tableName);
        //RDFHandler handler = new TRDatabaseMultilineSQLWriterHandler(predicates, pw, tableName, batchSize);
        //RDFHandler handler = new TRDatabaseCSVWriterHandler(predicates, pw, tableName);

        SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("statements", statements);
        ((Trackable)handler).bind(sofarTracker);
        RDFParser rdfParser = Rio.createParser(rdfFormat);
        rdfParser.setRDFHandler(handler);
        new Thread(new SofarWatchDog(sofarTracker)).start();

        rdfParser.parse(inputStream, namespace);

        logger.info(":: finish loading");

        // TODO add row counter
        return 0L;
    }

    static InputStream autoDetectCompressedStream(@Nonnull String path) throws IOException {
        if (path.endsWith(".gz")) {
            logger.debug("GZIPInputStream detected");
            return new GZIPInputStream(new FileInputStream(path));
        } else {
            logger.debug("Raw InputStream detected");
            return new FileInputStream(path);
        }
    }

    static RDFFormat autoDetectRDFFormat(String path) {
        String trimmed = path.endsWith(".gz") ? path.substring(0, path.length() - ".gz".length()) : path;
        if (trimmed.endsWith(".ttl")) {
            logger.debug("RDFFormat.TURTLE detected");
            return RDFFormat.TURTLE;
        } else if (trimmed.endsWith(".ntriples")) {
            logger.debug("RDFFormat.NTRIPLES");
            return RDFFormat.NTRIPLES;
        } else {
            throw new IllegalArgumentException("Unable to detect RDF format by " + path);
        }
    }

    public static void processFile(@Nonnull String path, String namespace, RDFFormat rdfFormat, PrintWriter printWriter, String tableName, int batchSize) throws Exception {


        // Count statements
        long statements = countStatements(autoDetectCompressedStream(path), rdfFormat, namespace);
        logger.info("Detected {} RDF statements in source file");

        MDC.put("mode", "DDL and stats");
        // Process generate table DDL & gather stats
        Map<IRI, Pair<String, String>> predicates = ddlAndPredicates(autoDetectCompressedStream(path), statements, rdfFormat, namespace, tableName);

        MDC.put("mode", "Generate SQL");
        // Load
        long dataRows = load(autoDetectCompressedStream(path), statements, rdfFormat, predicates, namespace, printWriter, tableName, batchSize);
        logger.info(":: generated dataRows = {}", dataRows);
        MDC.remove("mode");
    }

    static Options createOptions() {
        return new Options()
                .addOption("h", "print help")
                .addOption("s", "source", true, "source ex: ~/source.ttl.gz")
                .addOption("d", "dest", true, "dest ex: ~/dest.sql")
                .addOption("n", "namespace", true, "ns ex: 'ns'")
                .addOption("t", "tablename", true, "ex: mytable")
                .addOption("b", "dbname", true, "ex: postgres");
    }

    static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Usage: java -jar tr-loader.jar [options]", createOptions());
    }

    public static void main(String[] args) throws Exception {
        logger.info("START!");
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(createOptions(), args);
        if (commandLine.hasOption("h")) {
            printHelp();
            return;
        }
        PropertyService ps = PropertyService.createInstance();
        ps.setCommandLine(commandLine);

        Arrays.asList(args).stream()
                .forEach(arg -> logger.info("Command line argument : {}", arg));

        String source    = ps.lookupProperty("source");
        String namespace = ps.lookupProperty("namespace");
        String dest      = ps.lookupProperty("dest");
        String tableName = ps.lookupProperty("tablename");

        int batchSize = 50;

        new File(dest.substring(0, dest.lastIndexOf('/'))).mkdirs();

        PrintWriter printWriter = new PrintWriter(dest, "UTF-8");
        processFile(source, namespace, autoDetectRDFFormat(source), printWriter, tableName, batchSize);
        printWriter.flush();

        // DETAIL:  Cannot enlarge string buffer containing 0 bytes by 1 694 763 225 more bytes.
        logger.info(":: psql -d {} -a -f '{}'", ps.lookupProperty("dbname"), dest);
        // > select count(*) from org;
        //  count
        //---------
        // 5 131 497
        //(1 row)
        printWriter.close();
        logger.info("FINISH!");
    }

}
