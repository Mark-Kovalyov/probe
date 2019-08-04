package probe.mayton.orc;

import com.google.common.primitives.Bytes;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.orc.CompressionKind;
import org.apache.orc.OrcFile;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

import static probe.mayton.orc.Utils.ip;

public class CsvToStructuredOrc {

    static Logger logger = Logger.getLogger(CsvToStructuredOrc.class);

    public static Properties getProps() throws IOException {
        Properties properties = new Properties();
        if (new File("sensitive.properties").exists()) {
            properties.load(new FileInputStream("sensitive.properties"));
        } else {
            properties.put("maxMindCsvFile", "~/maxMindCsvFile.csv");
        }
        return properties;
    }

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("log4j.properties");

        Configuration conf = new Configuration();
        conf.set("key", "valyue");

        TypeDescription schema = TypeDescription.fromString(
                "struct<ipBlock:struct<startIpNum:bigint,endIpNum:bigint>,"   +
                        "country:string,"    +
                        "region:string,"     +
                        "city:string,"       +
                        "postalCode:string," +
                        "coordinates:struct<latitude:float,longitude:float>,"   +
                        "dmaCode:string,"    +
                        "areaCode:string>");

        Properties props = getProps();

        String maxMindCsvFile = props.getProperty("maxMindCsvFile");

        FileReader reader = new FileReader(maxMindCsvFile);

        CSVParser csvParser = CSVFormat.DEFAULT
                .withQuote('"')
                .withDelimiter(',')
                .withSkipHeaderRecord(true)
                .withFirstRecordAsHeader()
                .parse(reader);

        int cnt = 0;

        Writer writer = OrcFile.createWriter(
                new Path("files/maxmind-structured.orc"),
                OrcFile.writerOptions(conf)
                        .setSchema(schema)
                        .overwrite(true)
                        .rowIndexStride(0)
                        .compress(CompressionKind.NONE)
        );

        VectorizedRowBatch batch = schema.createRowBatch();

        LongColumnVector   startIpNum = (LongColumnVector) batch.cols[0];
        LongColumnVector   endIpNum   = (LongColumnVector) batch.cols[1];
        BytesColumnVector  country    = (BytesColumnVector) batch.cols[2];
        BytesColumnVector  region     = (BytesColumnVector) batch.cols[3];
        BytesColumnVector  city       = (BytesColumnVector) batch.cols[4];
        BytesColumnVector  postalCode = (BytesColumnVector) batch.cols[5];
        StructColumnVector coordinates = new StructColumnVector();

        DoubleColumnVector latitude   = (DoubleColumnVector) batch.cols[6];
        DoubleColumnVector longitude  = (DoubleColumnVector) batch.cols[7];
        BytesColumnVector  dmaCode    = (BytesColumnVector) batch.cols[8];
        BytesColumnVector  areaCode   = (BytesColumnVector) batch.cols[9];



        Iterator<CSVRecord> i = csvParser.iterator();
        while (i.hasNext()) {
            CSVRecord record = i.next();
            int row = batch.size++;
            startIpNum.vector[row] = ip(record.get(0));
            endIpNum.vector[row]   = ip(record.get(1));
            country.vector[row]    = record.get(2).getBytes();
            region.vector[row]     = record.get(3).getBytes();
            city.vector[row]       = record.get(4).getBytes();
            postalCode.vector[row] = record.get(5).getBytes();
            latitude.vector[row]   = Double.parseDouble(record.get(6));
            longitude.vector[row]  = Double.parseDouble(record.get(7));
            dmaCode.vector[row]    = record.get(8).getBytes();
            areaCode.vector[row]   = record.get(9).getBytes();

            if (batch.size == batch.getMaxSize()) {
                writer.addRowBatch(batch);
                batch.reset();
            }
            cnt++;
        }

        if (batch.size != 0) {
            writer.addRowBatch(batch);
            batch.reset();
        }

        writer.close();

        logger.info("Finished. Records : " + cnt);



    }

}
