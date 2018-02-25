package mayton.probe;

import oracle.jdbc.OracleDriver;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import static java.lang.System.getProperty;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FixIndexer {

    static Logger logger = LogManager.getLogger(FixIndexer.class);

    public static void processMessage(Document document,String message) {
        // TODO: Continue
    }

    public static void main(String[] args) throws Exception {
        logger.info("Begin");

        String outputDir = "output/fix-indexer";
        DriverManager.registerDriver(new OracleDriver());

        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
        Directory directory = FSDirectory.open(Paths.get(outputDir));
        IndexWriterConfig config = new IndexWriterConfig(standardAnalyzer);

        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter writer = new IndexWriter(directory, config);

        Connection conn = DriverManager.getConnection(getProperty("fix.indexer.jdbc.url"));

        Statement st = conn.createStatement();

        String sql = FileUtils.readFileToString(new File(getProperty("fix.indexer.sql")), UTF_8);

        ResultSet resultSet = st.executeQuery(sql);

        while(resultSet.next()) {
            String message = resultSet.getString("message");
            Document document = new Document();
            processMessage(document, message);
            writer.addDocument(document);
            logger.info("message = {} wrote", message);
        }

        resultSet.close();

        conn.close();

        writer.close();

        logger.info("End");
    }

}
