package mayton.probeavro;

import mayton.lib.NetworkUtils;
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

public class GeoIpLoader {

    static Logger logger = LoggerFactory.getLogger(GeoIpLoader.class);

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("sensitive.properties"));

        OutputStream outputStream = new FileOutputStream(properties.getProperty("target"));

        CSVParser parser = CSVParser.parse(new FileInputStream(properties.getProperty("source")), StandardCharsets.UTF_8,
                CSVFormat.DEFAULT
                        .withDelimiter(',')
                        .withFirstRecordAsHeader());

        Iterator<CSVRecord> irec = parser.iterator();
        int n = 0;
        BinaryMessageEncoder<GeoIpCityAvroEntity> encoder = GeoIpCityAvroEntity.getEncoder();
        while (irec.hasNext()) {

            CSVRecord rec = irec.next();
            GeoIpCityAvroEntity entity = GeoIpCityAvroEntity.newBuilder()
                    .setStartIpNum((int) NetworkUtils.parseIpV4(rec.get(0)))
                    .setEndIpNum((int) NetworkUtils.parseIpV4(rec.get(1)))
                    .setCountry(rec.get(2))
                    .setRegion(rec.get(3))
                    .setCity(rec.get(4))
                    .setPostalCode(rec.get(5))
                    .setLatitude(Double.parseDouble(rec.get(6)))
                    .setLongitude(Double.parseDouble(rec.get(7)))
                    .setDmaCode(rec.get(8))
                    .setAreaCode(rec.get(9))
                    .build();

            encoder.encode(entity, outputStream);

        }
        parser.close();

        outputStream.close();

    }



}
