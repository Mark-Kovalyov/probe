package mayton.probe.docindexer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class DocConsumer implements Consumer<InputStream> {

    static Logger logger = LogManager.getLogger(DocConsumer.class);

    public static  Document processDocument() {
        Document document = new Document();
        document.add(new StringField("filename",  "", Field.Store.YES));
        document.add(new StringField("extension", "", Field.Store.YES));
        document.add(new StringField("created", "", Field.Store.YES));
        document.add(new TextField("extension", "", Field.Store.YES));

        return document;
    }

    public static void main(String[] args) throws Exception {
        logger.info("Begin");

        String outputDir = "/db/lucene/doc-indexer";

        Directory directory = FSDirectory.open(Paths.get(outputDir));

        Analyzer analyzer = new SimpleAnalyzer();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        IndexWriter writer = new IndexWriter(directory, config);

        Document document = processDocument();

        writer.addDocument(document);

        writer.close();
        logger.info("Exit");
    }

    @Override
    public void accept(InputStream inputStream) {

    }
}
