package mayton.probeavro;

import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Properties;

public class GeoIpBinaryEncoder {

    static Logger logger = LoggerFactory.getLogger(GeoIpBinaryEncoder.class);

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("sensitive.properties"));

        try(OutputStream outputStreamAvro = new FileOutputStream(properties.getProperty("target.avro"))) {

            CSVParser parser = CSVParser.parse(new FileInputStream(properties.getProperty("source")), StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT
                            .withDelimiter(',')
                            .withFirstRecordAsHeader());

            Iterator<CSVRecord> irec = parser.iterator();

            // Avro
            BinaryMessageEncoder<GeoIpCityAvroEntity> binaryMessageEncoder = GeoIpCityAvroEntity.getEncoder();

            while (irec.hasNext()) {

                CSVRecord rec = irec.next();
                int startIpNum = 0;//(int) NetworkUtils.parseIpV4(rec.get(0));
                int endIpNum = 0;//(int) NetworkUtils.parseIpV4(rec.get(1));

                GeoIpCityAvroEntity entity = GeoIpCityAvroEntity.newBuilder()
                        .setStartIpNum(startIpNum)
                        .setEndIpNum(endIpNum)
                        .setCountry(rec.get(2))
                        .setRegion(rec.get(3))
                        .setCity(rec.get(4))
                        .setPostalCode(rec.get(5))
                        .setLatitude(Double.parseDouble(rec.get(6)))
                        .setLongitude(Double.parseDouble(rec.get(7)))
                        .setDmaCode(rec.get(8))
                        .setAreaCode(rec.get(9))
                        .build();

                binaryMessageEncoder.encode(entity, outputStreamAvro);

            }
            parser.close();
        }

    }



}
