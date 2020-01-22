package mayton.lucene;

import mayton.probe.SohAnalyzer;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Indexer {

    public static Options createOptions() {
        Options options = new Options();
        options.addOption(new Option("s", "sourceDir", false, "Source Dir (default = . )"));
        options.addOption(new Option("i", "indexDir", false, "Index Dir (default = ./.index/"));
        return options;
    }

    static Logger logger = LogManager.getLogger(Indexer.class);

    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new SimpleAnalyzer();
        logger.info(":: automatically detected Analyzer of instance = {}", analyzer.getClass());
        Directory directory = FSDirectory.open(Paths.get("/ignite-store/.index"));
        logger.info(":: automatically detected Directory of instance = {}", directory.getClass());
        // Directory directory = new SimpleFSDirectory(Paths.get("/documents/.index/"));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter writer = new IndexWriter(directory, config);
        DocVizitor docVizitor = new DocVizitor(writer);
        Path startPath = Paths.get("/documents");
        Files.walkFileTree(startPath, docVizitor);
        writer.close();
    }

}
