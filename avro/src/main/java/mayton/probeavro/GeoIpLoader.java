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

public class GeoIpLoader {

    static Logger logger = LoggerFactory.getLogger(GeoIpLoader.class);

    public static void main(String[] args) throws Exception {

        // startIpNum,endIpNum,country,region,city,postalCode,latitude,longitude,dmaCode,areaCode
        // 1.0.0.0,1.7.255.255,"AU","","","",-27.0000,133.0000,,
        OutputStream outputStream = new FileOutputStream("target/geo-ip-city.avro");

        CSVParser parser = CSVParser.parse(new FileInputStream("/db/geoip/03.GeoIPCity.csv"), StandardCharsets.UTF_8,
                CSVFormat.DEFAULT
                        .withDelimiter(',')
                        .withFirstRecordAsHeader());

        Iterator<CSVRecord> irec = parser.iterator();
        int n = 0;
        BinaryMessageEncoder<GeoIpCityAvroEntity> encoder = GeoIpCityAvroEntity.getEncoder();
        while (irec.hasNext()) {

            CSVRecord rec = irec.next();
            GeoIpCityAvroEntity entity = GeoIpCityAvroEntity.newBuilder()
                    .setStartIpNum((int) NetworkUtils.parseIpV4(rec.get(0) == null ? "0.0.0.0" : rec.get(0)))
                    .setEndIpNum((int) NetworkUtils.parseIpV4(rec.get(1) == null ? "0.0.0.0" : rec.get(1)))
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
