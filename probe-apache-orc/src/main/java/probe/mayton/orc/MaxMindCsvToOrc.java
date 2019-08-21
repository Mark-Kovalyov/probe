package probe.mayton.orc;

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

import static java.nio.charset.StandardCharsets.UTF_8;
import static probe.mayton.orc.Utils.getProps;
import static probe.mayton.orc.Utils.ip;

/**
 * <pre>
 *     "struct<startIpNum:bigint,"  +
 *                         "endIpNum:bigint,"   +
 *                         "country:string,"    +
 *                         "region:string,"     +
 *                         "city:string,"       +
 *                         "postalCode:string," +
 *                         "latitude:float,"    +
 *                         "longitude:float,"   +
 *                         "dmaCode:string,"    +
 *                         "areaCode:string>
 * </pre>
 */
public class MaxMindCsvToOrc {

    static Logger logger = Logger.getLogger(MaxMindCsvToOrc.class);

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("log4j.properties");

        Configuration conf = new Configuration();

        TypeDescription schema = TypeDescription.createStruct()
                .addField("startIpNum", TypeDescription.createLong())
                .addField("endIpNum",   TypeDescription.createLong())
                .addField("country",    TypeDescription.createString())
                .addField("region",     TypeDescription.createString())
                .addField("city",       TypeDescription.createString())
                .addField("postalCode", TypeDescription.createString())
                .addField("latitude",   TypeDescription.createDouble())
                .addField("longitude",  TypeDescription.createDouble())
                .addField("dmaCode",    TypeDescription.createString())
                .addField("areaCode",   TypeDescription.createString());


        Properties props = getProps();

        String maxMindCsvFile = props.getProperty("maxMindCsvFile");
        String maxMindOrcFile = props.getProperty("maxMindOrcFile");

        FileReader reader = new FileReader(maxMindCsvFile);

        CSVParser csvParser = CSVFormat.DEFAULT
                .withQuote('"')
                .withDelimiter(',')
                .withSkipHeaderRecord(true)
                .withFirstRecordAsHeader()
                .parse(reader);

        int cnt = 0;

        try(Writer writer = OrcFile.createWriter(
                new Path("files/" + maxMindOrcFile),
                OrcFile.writerOptions(conf)
                        .setSchema(schema)
                        .blockSize(4L * 1024 * 1024)
                        .stripeSize(256L * 1024 * 1024)
                        .useUTCTimestamp(true)
                        .rowIndexStride(1000)
                        .bloomFilterColumns("country,region,city,dmaCode,areaCode")
                        .enforceBufferSize()
                        .overwrite(true)
                        .compress(CompressionKind.ZLIB)
        )) {

            // Original CSV :  432 144 345
            // Orc.NONE     :  198 590 473
            // Orc.SNAPPY   :  116 356 823
            // Orc.ZLIB     :   72 376 814

            VectorizedRowBatch batch = schema.createRowBatch();

            LongColumnVector startIpNum = (LongColumnVector) batch.cols[0];
            LongColumnVector endIpNum = (LongColumnVector) batch.cols[1];
            BytesColumnVector country = (BytesColumnVector) batch.cols[2];
            BytesColumnVector region = (BytesColumnVector) batch.cols[3];
            BytesColumnVector city = (BytesColumnVector) batch.cols[4];
            BytesColumnVector postalCode = (BytesColumnVector) batch.cols[5];
            DoubleColumnVector latitude = (DoubleColumnVector) batch.cols[6];
            DoubleColumnVector longitude = (DoubleColumnVector) batch.cols[7];
            BytesColumnVector dmaCode = (BytesColumnVector) batch.cols[8];
            BytesColumnVector areaCode = (BytesColumnVector) batch.cols[9];


            Iterator<CSVRecord> i = csvParser.iterator();
            while (i.hasNext()) {
                CSVRecord record = i.next();
                int row = batch.size++;
                /////////////////////////////
                startIpNum.vector[row] = ip(record.get(0));
                endIpNum.vector[row] = ip(record.get(1));
                /////////////////////////////
                String countryField = record.get(2);

                //logger.info(format("countryField = '%s', size in bytes = %d",countryField, countryField.getBytes(StandardCharsets.UTF_8).length));

                country.setVal(row, countryField.getBytes(UTF_8));
                region.setVal(row, record.get(3).getBytes(UTF_8));
                city.setVal(row, record.get(4).getBytes(UTF_8));
                postalCode.setVal(row, record.get(5).getBytes(UTF_8));
                /////////////////////////////
                latitude.vector[row] = Double.parseDouble(record.get(6));
                longitude.vector[row] = Double.parseDouble(record.get(7));
                /////////////////////////////
                dmaCode.setVal(row, record.get(8).getBytes(UTF_8));
                areaCode.setVal(row, record.get(9).getBytes(UTF_8));
                /////////////////////////////
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

            logger.info("Finished. Records : " + cnt);

        }

    }

}
