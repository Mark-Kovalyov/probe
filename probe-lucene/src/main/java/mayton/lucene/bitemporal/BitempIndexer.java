package mayton.lucene.bitemporal;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BitempIndexer {

    static Logger logger = LogManager.getLogger(BitempIndexer.class);

    public static Options createOptions() {
        Options options = new Options();
        options.addOption(new Option("s", "sourceBitemporalDir", true, "Source Dir that contains bi-temporal CSV gzipped files"));
        options.addOption(new Option("i", "indexDir", true, "Index Dir"));
        options.addOption(new Option("a", "analyzer", true, "( core.SimpleAnalyzer (=default) | en.EnglishAnalyzer | standard.StandardAnalyzer ... in package 'org.apache.lucene.analysis' )"));
        return options;
    }

    public static void main(String[] args) throws ParseException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {

        if (args.length == 0) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar BitempIndexer", createOptions() );
            return;
        }
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(createOptions(), args);
        String analyzerClassName = cmd.hasOption('a') ? cmd.getOptionValue('a') : "core.SimpleAnalyzer";
        Class<?> c = Class.forName("org.apache.lucene.analysis." + analyzerClassName);
        Analyzer analyzer = (Analyzer) c.getDeclaredConstructor().newInstance();
        logger.info(":: detected Analyzer of instance = {}", analyzer.getClass());
        String indexDir = cmd.hasOption("i") ?
                cmd.getOptionValue("i") + "/" + Version.LATEST :
                ".index/" + Version.LATEST;

        Directory directory = FSDirectory.open(Paths.get(indexDir));
        logger.info(":: detected Directory of instance = {}", directory.getClass());
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setCommitOnClose(true);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter writer = new IndexWriter(directory, config);
        GZipVizitor gZipVizitor = new GZipVizitor(writer);
        String sourceDir = cmd.hasOption("s") ? cmd.getOptionValue("s") : ".";
        logger.info(":: detected sourceDir = {}", sourceDir);
        Path startPath = Paths.get(sourceDir);
        Files.walkFileTree(startPath, gZipVizitor);
        writer.commit();
        writer.close();
        logger.info(":: Done! Processed: {} files, {} Bytes", gZipVizitor.getProcessedFiles(), gZipVizitor.getProcessedBytes());

    }

}
