package mayton.probe;

import java.io.IOException;
import static java.lang.System.getProperty;
import java.nio.file.Paths;
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


public class FixSearcher {
    
    static Logger logger = LogManager.getLogger(FixSearcher.class);

    public static void main(String[] args) throws IOException, ParseException {
        
        logger.info("Begin");
        Analyzer analyzer = new SohAnalyzer();
        
        String outputDir = "output/fix-indexer";
        // Now search the index:
        Directory directory = FSDirectory.open(Paths.get(getProperty("fix.indexer.outputDir", outputDir)));
        
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("message", analyzer);
        Query query = parser.parse("35");
          
        ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
        
        // Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
        }
        ireader.close();
        directory.close();

        logger.info("Begin");
        
        
        
    }
    
}
