package probe.mayton.orc;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.orc.CompressionKind;
import org.apache.orc.OrcFile;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Properties;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

public class OmskToOrc {


    static Logger logger = Logger.getLogger(OmskToOrc.class);

    @NotNull
    public static Properties getProps() throws IOException {
        Properties properties = new Properties();
        if (new File("sensitive.properties").exists()) {
            properties.load(new FileInputStream("sensitive.properties"));
        } else {
            properties.put("omskCsvFile", "~/omskCsvFile.csv");
        }
        return properties;
    }

    // 25.2.1998
    public static long parseDateToLong(String field) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.M.yyyy h:mm:ss");
        long res = -1L;
        try {
            res = simpleDateFormat.parse(field).getTime();
        } catch (ParseException pe) {
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("log4j.properties");

        Configuration conf = new Configuration();

        // raion;adr;tel;datar;pasps;paspn;wd;fam;im;otch

        // TODO: Investegate for org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector
        // TODO: Investegate for 'timestamp'/'date' ORC-types
        /*
        TypeDescription schema = TypeDescription.fromString(
                "struct<raion:string,"  +
                        "adr:string," +
                        "tel:string,"  +
                        "datar:timestamp," +
                        "pasps:string," +
                        "paspn:string," +
                        "wd:string," +
                        "fam:string," +
                        "im:string," +
                        "otch:string>");*/

        TypeDescription schema = TypeDescription.createStruct()
                .addField("raion",    TypeDescription.createString())
                .addField("adr",      TypeDescription.createString())
                .addField("tel",      TypeDescription.createString())
                .addField("datar",    TypeDescription.createLong())
                .addField("pasps",    TypeDescription.createString())
                .addField("paspn",    TypeDescription.createString())
                .addField("wd",       TypeDescription.createString())
                .addField("fam",      TypeDescription.createString())
                .addField("im",       TypeDescription.createString())
                .addField("otch",     TypeDescription.createString());

        Properties props = getProps();

        String omskCsvFile = props.getProperty("omskCsvFile");
        String omskOrcFile = props.getProperty("omskOrcFile");

        logger.info("Parsing " + omskCsvFile);

        FileReader reader = new FileReader(omskCsvFile);

        CSVParser csvParser = CSVFormat.DEFAULT
                .withDelimiter(';')
                .withSkipHeaderRecord(true)
                .withFirstRecordAsHeader()
                .parse(reader);

        int cnt = 0;

        logger.info("Saving to " + "files/" + omskOrcFile);

        Writer writer = OrcFile.createWriter(
                new Path("files/" + omskOrcFile),
                OrcFile.writerOptions(conf)
                        .setSchema(schema)
                        .blockSize(4L * 1024 * 1024)
                        .stripeSize(256L * 1024 * 1024)
                        .useUTCTimestamp(true)
                        .rowIndexStride(1000)
                        .bloomFilterColumns("raion,adr,tel,pasps,paspn,wd,fam,im,otch")
                        .enforceBufferSize()
                        .overwrite(true)
                        .compress(CompressionKind.ZLIB)
        );

        VectorizedRowBatch batch = schema.createRowBatch();

        logger.info(format("Createt vectorized batch with maxsize = %d", batch.getMaxSize()));

        BytesColumnVector raion      = (BytesColumnVector) batch.cols[0];
        BytesColumnVector adr        = (BytesColumnVector) batch.cols[1];
        BytesColumnVector tel        = (BytesColumnVector) batch.cols[2];
        LongColumnVector  datar      = (LongColumnVector)  batch.cols[3];
        BytesColumnVector pasps      = (BytesColumnVector) batch.cols[4];
        BytesColumnVector paspn      = (BytesColumnVector) batch.cols[5];
        BytesColumnVector wd         = (BytesColumnVector) batch.cols[6];
        BytesColumnVector fam        = (BytesColumnVector) batch.cols[7];
        BytesColumnVector im         = (BytesColumnVector) batch.cols[8];
        BytesColumnVector otch       = (BytesColumnVector) batch.cols[9];

        Iterator<CSVRecord> i = csvParser.iterator();
        while (i.hasNext()) {
            CSVRecord record = i.next();

            String raionField = record.get(0);
            String adrField = record.get(1);
            String telField = record.get(2);
            String datarField = record.get(3);
            String paspsField = record.get(4);
            String paspnField = record.get(5);
            String wdField = record.get(6);
            String famField = record.get(7);
            String imField = record.get(8);
            String otchField = record.get(9);

            int row = batch.size++;

            logger.trace(format("row = %d, batch row = %d, datarField = '%s'",cnt, row, datarField));

            raion.setVal(row, raionField.getBytes(UTF_8));
            adr.setVal(row, adrField.getBytes(UTF_8));
            tel.setVal(row, telField.getBytes(UTF_8));
            datar.vector[row] = parseDateToLong(datarField);
            pasps.setVal(row, paspsField.getBytes(UTF_8));
            paspn.setVal(row, paspnField.getBytes(UTF_8));
            wd.setVal(row, wdField.getBytes(UTF_8));
            fam.setVal(row, famField.getBytes(UTF_8));
            im.setVal(row, imField.getBytes(UTF_8));
            otch.setVal(row, otchField.getBytes(UTF_8));

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
