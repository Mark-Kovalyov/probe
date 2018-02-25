package mayton.probe;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Iterator;

import static java.lang.System.getProperty;

public class GeoIndexer {

    static Logger logger = LogManager.getLogger(GeoIndexer.class);

    public static void main(String[] args) throws Exception {

        logger.info("Begin");

        String inputFilePath = getProperty("geo.input.file");
        String outputDir     = "output/geo-indexer";

        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));

        CSVParser parser = new CSVParser(br, CSVFormat.newFormat(',').withFirstRecordAsHeader());

        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();

        Directory directory = FSDirectory.open(Paths.get(outputDir));

        IndexWriterConfig config = new IndexWriterConfig(standardAnalyzer);

        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter writer = new IndexWriter(directory, config);

        int cnt = 0;

        Iterator<CSVRecord> i = parser.iterator();
        while (i.hasNext()) {

            CSVRecord record = i.next();

            String startIpNum = record.get("startIpNum");
            String endIpNum   = record.get("endIpNum");
            String country    = record.get("country");
            String region     = record.get("region");
            String city       = record.get("city");
            String postalCode = record.get("postalCode");
            String dmaCode    = record.get("dmaCode");
            String areaCode   = record.get("areaCode");
            double latitude   = Double.valueOf(record.get("latitude"));
            double longitude  = Double.valueOf(record.get("longitude"));

            Document document = new Document();
            document.add(new StringField("startIpNum",  startIpNum, Field.Store.YES));
            document.add(new StringField("endIpNum",    endIpNum,   Field.Store.YES));
            document.add(new StringField("country",     country,    Field.Store.YES));
            document.add(new StringField("region",      region,     Field.Store.YES));
            document.add(new StringField("city",        city,       Field.Store.YES));
            document.add(new StringField("postalCode",  postalCode, Field.Store.YES));
            document.add(new StringField("dmaCode",     dmaCode,    Field.Store.YES));
            document.add(new StringField("areaCode",    areaCode,   Field.Store.YES));
            document.add(new StringField("latitude",    String.valueOf(latitude),  Field.Store.NO));
            document.add(new StringField("longitude",   String.valueOf(longitude), Field.Store.NO));
            document.add(new StringField("postalCode",  postalCode, Field.Store.YES));

            writer.addDocument(document);
            cnt++;
            if (cnt % 10_000 == 0) {
                logger.info("Proceed {} rows", cnt);
            }
        }
        parser.close();
        writer.close();
        logger.info("End");

    }

}
