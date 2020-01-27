package mayton.lucene;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;

import static java.lang.System.getProperty;

public class Searcher {

    public static Options createOptions() {
        Options options = new Options();
        options.addOption(new Option("i", "indexDir", true, "Index Dir"));
        options.addOption(new Option("a", "analyzer", true, "( core.SimpleAnalyzer | en.EnglishAnalyzer | ru.RussianAnalyzer ...)"));
        options.addOption(new Option("e", "expression", true, "Search expr"));
        return options;
    }

    static Logger logger = LogManager.getLogger(Indexer.class);

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ParseException, org.apache.commons.cli.ParseException {
        if (args.length == 0) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "Indexer", createOptions() );
            return;
        }
        logger.info("Begin");
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cmd = commandLineParser.parse(createOptions(), args);

        Class<?> c = Class.forName("org.apache.lucene.analysis." + "core.SimpleAnalyzer");
        Analyzer analyzer = (Analyzer) c.getDeclaredConstructor().newInstance();

        Directory directory = FSDirectory.open(Paths.get(cmd.getOptionValue('i')));

        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("body", analyzer);
        Query query = parser.parse(cmd.getOptionValue('e'));

        ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;

        // Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            System.out.printf("Found : " + hitDoc.toString());
        }

        ireader.close();
        directory.close();

        logger.info("Begin");

    }

}
