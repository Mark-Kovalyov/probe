package mayton.lucene;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Indexer {

    public static Options createOptions() {
        Options options = new Options();
        options.addOption(new Option("s", "sourceDir", true, "Source Dir"));
        options.addOption(new Option("i", "indexDir", true, "Index Dir"));
        //options.addOption(new Option("e", "extensions", true, "Comma-separated extensions ex: txt,html"));
        return options;
    }

    static Logger logger = LogManager.getLogger(Indexer.class);

    public static void main(String[] args) throws IOException, ParseException {
        if (args.length == 0) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "Indexer", createOptions() );
            return;
        }
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(createOptions(), args);
        Analyzer analyzer = new SimpleAnalyzer(); //new EnglishAnalyzer();
        logger.info(":: detected Analyzer of instance = {}", analyzer.getClass());
        String indexDir = cmd.hasOption("i") ?
                cmd.getOptionValue("i") + "/" + Version.LATEST :
                ".index/" + Version.LATEST;

        Directory directory = FSDirectory.open(Paths.get(indexDir)); //Paths.get("/ignite-store/.english-analyzer-index"));
        logger.info(":: detected Directory of instance = {}", directory.getClass());
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setCommitOnClose(true);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter writer = new IndexWriter(directory, config);
        DocVizitor docVizitor = new DocVizitor(writer);
        String sourceDir = cmd.hasOption("s") ? cmd.getOptionValue("s") : ".";
        logger.info(":: detected sourceDir = {}", sourceDir);
        Path startPath = Paths.get(sourceDir);
        Files.walkFileTree(startPath, docVizitor);
        writer.close();
        logger.info(":: Done!");
    }

}
