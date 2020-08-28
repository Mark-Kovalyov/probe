package mayton.semanticweb;

import mayton.lib.SofarTracker;
import mayton.semanticweb.rdfhandlers.TRDatabaseCountingHandler;
import mayton.semanticweb.rdfhandlers.TRDatabaseDDLAnalyzer;
import mayton.semanticweb.rdfhandlers.TRDatabaseSQLWriterHandler;
import org.apache.commons.cli.*;
import org.apache.commons.io.IOUtils;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

// TODO: Migrate to Spring-Boot or Guice!
public class TRLoader {

    static Logger logger = LoggerFactory.getLogger(TRLoader.class);

    public static long countOrGetStatements(String sourceRdfArchive, RDFFormat rdfFormat, String baseUri, String tableName) throws IOException {
        logger.info(":: Estimation");
        if (Files.exists(Path.of(sourceRdfArchive + ".cachedcount"))) {
            logger.info(":: Skipped. Already detected cached value from {}", sourceRdfArchive);
            long countSt = Long.valueOf(IOUtils.toString(new FileInputStream(sourceRdfArchive + ".cachedcount"), "UTF-8"));
            logger.info(":: cachedvalue = {}", countSt);
            return countSt;
        } else {
            RDFParser rdfParser = Rio.createParser(rdfFormat);
            TRDatabaseCountingHandler countingHandler = new TRDatabaseCountingHandler(tableName);
            rdfParser.setRDFHandler(countingHandler);
            rdfParser.parse(autoDetectCompressedStream(sourceRdfArchive), baseUri);
            PrintWriter pidWriter = new PrintWriter(sourceRdfArchive + ".cachedcount");
            pidWriter.print(countingHandler.getStatements());
            pidWriter.close();
            logger.info(":: Finish estimation. Wrote cached value {}", countingHandler.getStatements());
            return countingHandler.getStatements();
        }
    }

    public static Map<IRI, FieldDescriptor> createOrConvertDllScript(String path, long statements, RDFFormat rdfFormat, String namespace, String tableName, PrintWriter pw) throws Exception {
        logger.info(":: process DDL for table");
        RDFParser rdfParser = Rio.createParser(rdfFormat);
        SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("statements", statements);
        logger.info("{}", sofarTracker);
        TRDatabaseDDLAnalyzer trDatabaseDDLAnalyzer = new TRDatabaseDDLAnalyzer(tableName,pw);
        trDatabaseDDLAnalyzer.bind(sofarTracker);
        rdfParser.setRDFHandler(trDatabaseDDLAnalyzer);
        new Thread(new SofarWatchDog(sofarTracker)).start();
        rdfParser.parse(autoDetectCompressedStream(path), namespace);
        logger.info(":: finish processing DDL");
        Map<IRI, FieldDescriptor> fieldDescriptorMap = trDatabaseDDLAnalyzer.getFieldDescriptorMap();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(destfolder + "/" + destDdl + ".mapjbin"))) {
            oos.writeObject(fieldDescriptorMap);
        }
        trDatabaseDDLAnalyzer.close();
        return fieldDescriptorMap;
    }

    public static long sqlWrite(@Nonnull InputStream inputStream, long statements, RDFFormat rdfFormat, Map<IRI, FieldDescriptor> fieldDescriptorMap, String namespace, PrintWriter pw, String tableName) throws IOException {
        logger.info(":: start loading");
        TRDatabaseSQLWriterHandler trDatabaseSQLWriterHandler = new TRDatabaseSQLWriterHandler(fieldDescriptorMap, pw, tableName);
        SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("statements", statements);
        trDatabaseSQLWriterHandler.bind(sofarTracker);
        RDFParser rdfParser = Rio.createParser(rdfFormat);
        rdfParser.setRDFHandler(trDatabaseSQLWriterHandler);
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

    static List<RDFFormat> rdfFormats = Arrays.asList(
            RDFFormat.TRIG,
            RDFFormat.RDFXML,
            RDFFormat.TURTLE,
            RDFFormat.JSONLD,
            RDFFormat.NTRIPLES,
            RDFFormat.NQUADS
    );

    static RDFFormat autoDetectRDFFormat(String path) {
        String trimmed = path.endsWith(".gz") ? path.substring(0, path.length() - ".gz".length()) : path;
        for(RDFFormat currentRdfFormat : rdfFormats) {
            for(String extension : currentRdfFormat.getFileExtensions()) {
                if (trimmed.endsWith(extension)) {
                    return currentRdfFormat;
                }
            }
        }
        throw new IllegalArgumentException("Unable to detect RDF format by " + path);
    }

    public static void processFile(String sourceRdfArchive, String namespace, RDFFormat rdfFormat,
                                   String tableName) throws Exception {

        // Count statements (will prepare Sofar Tracker estimations)
        long statements = countOrGetStatements(sourceRdfArchive, rdfFormat, namespace, tableName);
        logger.info("Detected {} RDF statements in source file", statements);


        // Process generate table DDL & gather stats
        Map<IRI, FieldDescriptor> fieldDescriptorMap;

        if (Files.exists(Path.of(destfolder + "/" + destDdl + ".mapjbin"))) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(destfolder + "/" + destDdl + ".mapjbin"))) {
                fieldDescriptorMap = (Map<IRI, FieldDescriptor>) ois.readObject();
                logger.info("Field descriptor map loaded from cached *.mapjbin file");
            }
        } else {
            logger.info("Detecting field descriptor map");
            try(PrintWriter sqlDdlWriter = new PrintWriter(new FileOutputStream(destfolder + "/" + destDdl), true, StandardCharsets.UTF_8)) {
                fieldDescriptorMap = createOrConvertDllScript(
                        sourceRdfArchive,
                        statements,
                        rdfFormat,
                        namespace,
                        tableName,
                        sqlDdlWriter
                );
            }
            logger.info("Field descriptor map calculated");
        }

        // Load
        long dataRows = 0;
        try(PrintWriter sqlWriter = new PrintWriter(new FileOutputStream(destfolder + "/" + destDml), true, StandardCharsets.UTF_8)){
            dataRows = sqlWrite(
                    autoDetectCompressedStream(sourceRdfArchive),
                    statements,
                    rdfFormat,
                    fieldDescriptorMap,
                    namespace,
                    sqlWriter,
                    tableName);
        }
        logger.info(":: generated dataRows = {}", dataRows);
    }

    static Options createOptions() {
        return new Options()
                .addOption("h", "print help")
                .addOption("s", "source", true, "source ex: ~/source.ttl.gz")
                .addOption("d", "destfolder", true, "dest ex: ~/")
                .addOption("l", "destddl", true, "dest ex: dest-ddl.sql")
                .addOption("m", "destdml", true, "dest ex: dest-dml.sql")
                .addOption("n", "namespace", true, "ns ex: 'ns'")
                .addOption("t", "tablename", true, "ex: mytable")
                .addOption("b", "dbname", true, "ex: postgres")
                .addOption("p", "partitionby", true, "ex: 16");
    }

    static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Usage: java -jar tr-loader.jar [options]", createOptions());
    }

    static String source = null;
    static String namespace = null;
    static String destfolder = null;
    static String destDdl = null;
    static String destDml = null;
    static String tableName = null;

    public static void main(String[] args) throws Exception {
        logger.info("START!");
        long ts = System.currentTimeMillis();
        long pid = ProcessHandle.current().pid();
        PrintWriter pidWriter = new PrintWriter("tr-loader.pid");
        pidWriter.println(pid);
        pidWriter.close();
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

        // TODO: Unify with PropertyService
        source     = ps.lookupProperty("source");
        namespace  = ps.lookupProperty("namespace");
        destfolder = ps.lookupProperty("destfolder");
        destDdl    = ps.lookupProperty("destddl");
        destDml    = ps.lookupProperty("destdml");
        tableName  = ps.lookupProperty("tablename");

        new File(destfolder).mkdirs();

        processFile(
                source,
                namespace,
                autoDetectRDFFormat(source),
                tableName
        );

        Files.delete(Paths.get("tr-loader.pid"));
        logger.info("FINISH!");
    }

}
