package probe.mayton.orc;

import com.google.common.primitives.Bytes;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.orc.CompressionKind;
import org.apache.orc.OrcFile;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

public class CsvToOrc {

    static Logger logger = Logger.getLogger(CsvToOrc.class);

    // Sample:
    // -----------------------------------------------------------------------------------------
    //startIpNum,endIpNum,country,region,city,postalCode,latitude,longitude,dmaCode,areaCode
    //1.0.0.0,1.7.255.255,"AU","","","",-27.0000,133.0000,,
    //1.9.0.0,1.9.255.255,"MY","","","",2.5000,112.5000,,
    //1.10.10.0,1.10.10.255,"AU","","","",-27.0000,133.0000,,
    //1.11.0.0,1.11.255.255,"KR","","","",37.0000,127.5000,,
    //1.12.0.0,1.15.255.255,"CN","","","",35.0000,105.0000,,
    //1.16.0.0,1.19.255.255,"KR","","","",37.0000,127.5000,,
    //1.21.0.0,1.21.255.255,"JP","","","",36.0000,138.0000,,
    //1.22.0.0,1.22.255.255,"IN","","","",20.0000,77.0000,,
    //1.23.0.0,1.23.15.255,"IN","02","Hyderabad","",17.3753,78.4744,,

    public static Properties getProps() throws IOException {
        Properties properties = new Properties();
        if (new File("sensitive.properties").exists()) {
            properties.load(new FileInputStream("sensitive.properties"));
        } else {
            properties.put("maxMindCsvFile", "~/maxMindCsvFile.csv");
        }
        return properties;
    }

    public static int ip(String ips) {
        int[] ip = new int[4];
        String[] parts = ips.split("\\.");
        for (int i = 0; i < 4; i++) {
            ip[i] = Integer.parseInt(parts[i]);
        }
        return  ip[3] << 24 |
                ip[2] << 16 |
                ip[1] << 8 |
                ip[0] ;
    }

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("log4j.properties");

        Configuration conf = new Configuration();
        conf.set("key", "valyue");

        TypeDescription schema = TypeDescription.fromString(
                "struct<startIpNum:bigint,"  +
                        "endIpNum:bigint,"   +
                        "country:string,"    +
                        "region:string,"     +
                        "city:string,"       +
                        "postalCode:string," +
                        "latitude:float,"    +
                        "longitude:float,"   +
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
                new Path("files/maxmind.orc"),
                OrcFile.writerOptions(conf)
                        .setSchema(schema)
                        .overwrite(true)
                        .rowIndexStride(0)
                         /* Set the distance between entries in the row index. The minimum value is 1000 to prevent the
                          * index from overwhelming the data. If the stride is set to 0, no indexes will be included in the file.*/
                        .compress(CompressionKind.NONE) // { SNAPPY | ZLIB | NONE | LZO | LZ4 }
        );

        // Original CSV :  432 144 345
        // Orc.NONE     :   92 272 349
        // Orc.ZLIB     :   38 126 025

        VectorizedRowBatch batch = schema.createRowBatch();

            LongColumnVector   startIpNum = (LongColumnVector) batch.cols[0];
            LongColumnVector   endIpNum   = (LongColumnVector) batch.cols[1];
            BytesColumnVector  country    = (BytesColumnVector) batch.cols[2];
            BytesColumnVector  region     = (BytesColumnVector) batch.cols[3];
            BytesColumnVector  city       = (BytesColumnVector) batch.cols[4];
            BytesColumnVector  postalCode = (BytesColumnVector) batch.cols[5];
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
