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
import java.io.FileReader;
import java.util.Iterator;
import java.util.Properties;

import static java.lang.String.format;
import static probe.mayton.orc.Utils.getProps;

public class PrimesToOrc {

    static Logger logger = Logger.getLogger(PrimesToOrc.class);

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("log4j.properties");

        Configuration conf = new Configuration();

        TypeDescription schema = TypeDescription.createStruct()
                .addField("prime",         TypeDescription.createString())
                .addField("isRabinMiller", TypeDescription.createBoolean())
                .addField("isLukasLehmer", TypeDescription.createBoolean())
                .addField("isLukasLehmer", TypeDescription.createBoolean());

        Properties props = getProps();

        String primeCsvFile = props.getProperty("primesCsvFile");
        String primeOrcFile = props.getProperty("primesOrcFile");

        logger.info("Parsing " + primeCsvFile);

        FileReader reader = new FileReader(primeCsvFile);

        CSVParser csvParser = CSVFormat.DEFAULT
                .withDelimiter(';')
                .withSkipHeaderRecord(true)
                .withFirstRecordAsHeader()
                .parse(reader);

        int cnt = 0;

        logger.info("Saving to " + primeOrcFile);

        Writer writer = OrcFile.createWriter(
                new Path(primeOrcFile),
                OrcFile.writerOptions(conf)
                        .setSchema(schema)
                        .blockSize(4L * 1024 * 1024)
                        .stripeSize(256L * 1024 * 1024)
                        .useUTCTimestamp(true)
                        .rowIndexStride(0)
                        .enforceBufferSize()
                        .overwrite(true)
                        .compress(CompressionKind.NONE)
        );

        VectorizedRowBatch batch = schema.createRowBatch();

        logger.info(format("Createt vectorized batch with maxsize = %d", batch.getMaxSize()));

        LongColumnVector primes = (LongColumnVector) batch.cols[0];


        Iterator<CSVRecord> i = csvParser.iterator();
        while (i.hasNext()) {
            CSVRecord record = i.next();
            int row = batch.size++;
            primes.vector[row] = Long.valueOf(record.get(0));
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
