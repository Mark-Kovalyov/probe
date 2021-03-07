package probe.mayton.orc;

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
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static probe.mayton.orc.Utils.getProps;

/**
 * <PRE>
 *     struct<raion:string,
 *                         "adr:string," +
 *                         "tel:string,"  +
 *                         "datar:timestamp," +
 *                         "pasps:string," +
 *                         "paspn:string," +
 *                         "wd:string," +
 *                         "fam:string," +
 *                         "im:string," +
 *                         "otch:string>
 * </PRE>
 *
 *
 *
 */
public class OmskToOrc {

    static Logger logger = Logger.getLogger(OmskToOrc.class);

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.M.yyyy h:mm:ss");

    public static Optional<Long> parseDateToLong(@NotNull String field) {
        try {
            Date date = simpleDateFormat.parse(field);
            return Optional.of(getDifferenceDays(date, simpleDateFormat.parse("01.1.1970 0:00:00")));
        } catch (ParseException pe) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("log4j.properties");

        Configuration conf = new Configuration();

        TypeDescription schema = TypeDescription.createStruct()
                .addField("raion",    TypeDescription.createString())
                .addField("adr",      TypeDescription.createString())
                .addField("tel",      TypeDescription.createString())
                .addField("datar",    TypeDescription.createDate())
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

        try(Writer writer = OrcFile.createWriter(
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
                        .compress(CompressionKind.NONE)
        )) {
            VectorizedRowBatch batch = schema.createRowBatch();

            logger.info(format("Createt vectorized batch with maxsize = %d", batch.getMaxSize()));

            BytesColumnVector raion      = (BytesColumnVector) batch.cols[0];
            BytesColumnVector adr        = (BytesColumnVector) batch.cols[1];
            BytesColumnVector tel        = (BytesColumnVector) batch.cols[2];

            LongColumnVector  datar      = (LongColumnVector)  batch.cols[3]; // ORC Type = date, --> Column Vector = LongColumnVector


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
                String adrField   = record.get(1);
                String telField   = record.get(2);
                String datarField = record.get(3);
                String paspsField = record.get(4);
                String paspnField = record.get(5);
                String wdField    = record.get(6);
                String famField   = record.get(7);
                String imField    = record.get(8);
                String otchField  = record.get(9);

                int row = batch.size++;

                logger.trace(format("row = %d, batch row = %d, datarField = '%s'",cnt, row, datarField));

                raion.setVal(row, raionField.getBytes(UTF_8));
                adr.setVal(row, adrField.getBytes(UTF_8));
                tel.setVal(row, telField.getBytes(UTF_8));
                Optional<Long> datarOptional = parseDateToLong(datarField);
                if (datarOptional.isPresent()) {
                    datar.vector[row] = datarOptional.get();
                } else {
                    // TODO: How to set NULL ?
                }
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

        }

        logger.info("Finished. Records : " + cnt);



    }


}
