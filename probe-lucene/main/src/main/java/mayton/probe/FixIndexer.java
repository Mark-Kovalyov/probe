package mayton.probe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.System.getProperty;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.commons.io.IOUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.TextField;

/**
 * <pre>
 * DBMS_LOB.GETLENGTH (
 *     lob_loc    IN  BLOB) 
 *      RETURN INTEGER;
 * 
 * EMPTY_CLOB()
 * 
 * SYSTIMESTAMP()
 * 
 * SQL> insert into fixlog values(sys_guid(),systimestamp,empty_clob());
 * 
 * 
 * </pre>
 * @author mayton
 */
public class FixIndexer {

    static Logger logger = LogManager.getLogger(FixIndexer.class);
    
    public String splitter = getProperty("splitters","|");

    public static void processMessage(Document  document,
                                      String    messageId, 
                                      Timestamp ts, 
                                      Clob messageClob) throws SQLException, IOException {
        
        document.add(new StringField("messageId", messageId,     Field.Store.YES));
        document.add(new StringField("ts",        ts.toString(), Field.Store.YES));
        
        try(Reader reader = messageClob.getCharacterStream()){
            String message = IOUtils.toString(reader);
            document.add(new TextField("message", message,Field.Store.NO));
        }
    }

    public static void main(String[] args) throws Exception {
        logger.info("Begin");

        String outputDir        = "output/fix-indexer";
        String connectionString = "jdbc:oracle:thin:scott/tiger@127.0.0.1:1521/XE";

        Analyzer analyzer = new SohAnalyzer();
        Directory directory = FSDirectory.open(Paths.get(getProperty("fix.indexer.outputDir", outputDir)));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter writer = new IndexWriter(directory, config);

        Connection conn = DriverManager.getConnection(getProperty("fix.indexer.jdbc.BROKER_URL", connectionString));

        Statement st = conn.createStatement();

        String sql =    "select \n" +
                        "  messageid,\n" +
                        "  messagets,\n" +
                        "  message \n" +
                        "from \n" +
                        "  fixlog";

        ResultSet resultSet = st.executeQuery(sql);
        
        while(resultSet.next()) {
            String messageId = resultSet.getString("messageid");
            Timestamp     ts = resultSet.getTimestamp("messagets");
            Clob   message   = resultSet.getClob("message");
            if (message.length() > 0) {
                Document document = new Document();
                processMessage(document, messageId, ts, message);
                writer.addDocument(document);
                logger.info("message with id = {} has been written", messageId);
            } else {
                logger.info("message with id = {} has been skipped because empty field reason.", messageId);
            }
        }

        resultSet.close();
        conn.close();
        writer.close();
        logger.info("Exit");
        
    }

}
