package mayton.semanticweb;

import mayton.lib.SofarTracker;
import mayton.semanticweb.rdfhandlers.TRDatabaseCountingHandler;
import mayton.semanticweb.rdfhandlers.TRDatabaseDDLAnalyzer;
import mayton.semanticweb.rdfhandlers.TRDatabaseSQLWriterHandler;
import org.apache.commons.cli.*;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.swing.text.DateFormatter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.zip.GZIPInputStream;


public class TRLoader<dateFormatter> {

    static Logger logger = LoggerFactory.getLogger(TRLoader.class);

    public static long countStatements(InputStream inputStream, RDFFormat rdfFormat, String baseUri) throws IOException {
        logger.info(":: Estimation");
        RDFParser rdfParser = Rio.createParser(rdfFormat);
        TRDatabaseCountingHandler countingHandler = new TRDatabaseCountingHandler();
        rdfParser.setRDFHandler(countingHandler);
        rdfParser.parse(inputStream, baseUri);
        logger.info(":: Finish estimation");
        return countingHandler.getStatements();
    }

    public static Map<IRI, FieldDescriptor>  ddlAndPredicates(InputStream inputStream, long statements, RDFFormat rdfFormat, String namespace, String tableName) throws Exception {

        logger.info(":: process DDL for table");

        RDFParser rdfParser = Rio.createParser(rdfFormat);

        SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("statements", statements);
        logger.info("{}", sofarTracker.toString());

        TRDatabaseDDLAnalyzer trDatabaseDDLAnalyzer = new TRDatabaseDDLAnalyzer(tableName);
        trDatabaseDDLAnalyzer.bind(sofarTracker);

        rdfParser.setRDFHandler(trDatabaseDDLAnalyzer);
        ///new Thread(new SofarWatchDog(sofarTracker)).start();

        rdfParser.parse(inputStream, namespace);

        logger.info(":: finish processing DDL");

        Map<IRI, FieldDescriptor> res = trDatabaseDDLAnalyzer.getFieldDescriptorMap();

        trDatabaseDDLAnalyzer.close();

        return res;
    }

    public static long sqlWrite(@Nonnull InputStream inputStream, long statements, RDFFormat rdfFormat, Map<IRI, FieldDescriptor> fieldDescriptorMap, String namespace, PrintWriter pw, String tableName, int batchSize) throws Exception {

        logger.info(":: start loading");

        TRDatabaseSQLWriterHandler trDatabaseSQLWriterHandler = new TRDatabaseSQLWriterHandler(fieldDescriptorMap, pw, tableName);

        SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("statements", statements);
        trDatabaseSQLWriterHandler.bind(sofarTracker);

        RDFParser rdfParser = Rio.createParser(rdfFormat);
        rdfParser.setRDFHandler(trDatabaseSQLWriterHandler);

        //new Thread(new SofarWatchDog(sofarTracker)).start();

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


        // Count statements (will prepare Sofar Tracker estimations)
        long statements = countStatements(autoDetectCompressedStream(path), rdfFormat, namespace);
        logger.info("Detected {} RDF statements in source file");

        // Process generate table DDL & gather stats
        Map<IRI, FieldDescriptor> fieldDescriptorMap = ddlAndPredicates(
                autoDetectCompressedStream(path),
                statements,
                rdfFormat,
                namespace,
                tableName);

        // Load
        long dataRows = sqlWrite(
                autoDetectCompressedStream(path),
                statements,
                rdfFormat,
                fieldDescriptorMap,
                namespace,
                printWriter,
                tableName,
                batchSize);

        logger.info(":: generated dataRows = {}", dataRows);
    }

    static Options createOptions() {
        return new Options()
                .addOption("h", "print help")
                .addOption("s", "source", true, "source ex: ~/source.ttl.gz")
                .addOption("d", "dest", true, "dest ex: ~/dest.sql")
                .addOption("n", "namespace", true, "ns ex: 'ns'")
                .addOption("t", "tablename", true, "ex: mytable")
                .addOption("b", "dbname", true, "ex: postgres")
                .addOption("p", "partitionby", true, "ex: 16");
    }

    static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Usage: java -jar tr-loader.jar [options]", createOptions());
    }

    static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
        logger.info("START!");
        long ts = System.currentTimeMillis();
        logger.info("begin time = {}", new Date(ts));
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(createOptions(), args);
        if (commandLine.hasOption("h")) {
            printHelp();
            return;
        }
        PropertyService ps = PropertyService.getInstance();
        ps.setCommandLine(commandLine);

        Arrays.asList(args).stream()
                .forEach(arg -> logger.info("Command line argument : {}", arg));

        String source    = ps.lookupProperty("source");
        String namespace = ps.lookupProperty("namespace");
        String dest      = ps.lookupProperty("dest");
        String tableName = ps.lookupProperty("tablename");
        //String partitionBy = ps.lookupProperty("partitionby");

        int batchSize = 50;

        new File(dest.substring(0, dest.lastIndexOf('/'))).mkdirs();

        PrintWriter printWriter = new PrintWriter(new FileOutputStream(dest), true, StandardCharsets.UTF_8);
        processFile(source, namespace, autoDetectRDFFormat(source), printWriter, tableName, batchSize);
        printWriter.flush();

        printWriter.close();
        logger.info("FINISH!");
    }

}
