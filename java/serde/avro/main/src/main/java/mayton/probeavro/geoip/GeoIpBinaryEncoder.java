package mayton.probeavro.geoip;

import mayton.network.NetworkUtils;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.TimeInstrument;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Properties;

public class GeoIpBinaryEncoder {

    static Logger logger = LoggerFactory.getLogger(GeoIpBinaryEncoder.class);

    public static void main(String[] args) throws Exception {

        Profiler profiler = new Profiler("GeoIpBinaryEncoder");

        profiler.start("Export to binary avro");

        int cnt = 0;

        try(OutputStream outputStreamAvro = new FileOutputStream("dat/geo-ip-entity.avro")) {

            CSVParser parser = CSVParser.parse(new FileInputStream("/storage/db/GEO/maxmind/2010-10.MaxMind GeoIP City CSV Format/GeoIP-139_20101001/GeoIPCity.csv"), StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT
                            .withDelimiter(',')
                            .withFirstRecordAsHeader());

            Iterator<CSVRecord> irec = parser.iterator();

            // Avro
            BinaryMessageEncoder<GeoIpCityAvroEntity> binaryMessageEncoder = GeoIpCityAvroEntity.getEncoder();

            GeoIpCityAvroEntity forReuse = new GeoIpCityAvroEntity();
            while (irec.hasNext()) {

                CSVRecord rec = irec.next();
                int startIpNum = (int) NetworkUtils.parseIpV4(rec.get(0));
                int endIpNum = (int) NetworkUtils.parseIpV4(rec.get(1));

                GeoIpCityAvroEntity entity = GeoIpCityAvroEntity.newBuilder(forReuse)
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
                cnt++;
            }
            parser.close();
        }

        logger.info("cnt = {}", cnt);

        profiler.stop();

        TimeInstrument tm = profiler.stop();

        tm.print();

    }



}
