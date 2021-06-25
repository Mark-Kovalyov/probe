package mayton.probeavro.geoip;

import org.apache.avro.Schema;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.TimeInstrument;

import java.io.*;

public class GeoIpBinaryDecoder {

    static Logger logger = LoggerFactory.getLogger(GeoIpBinaryDecoder.class);

    public static void main(String[] args) {

        Profiler profiler = new Profiler("GeoIpBinaryDecoder");

        profiler.start("Reading from binary avro");
        int cnt = 0;
        try(InputStream is = new FileInputStream("dat/geo-ip-entity.avro")) {
            BinaryMessageDecoder<GeoIpCityAvroEntity> binaryMessageDecoder = GeoIpCityAvroEntity.getDecoder();
            GeoIpCityAvroEntity geoIpCity = new GeoIpCityAvroEntity();
            //Schema.Parser parser = new Schema.Parser();
            //binaryMessageDecoder.addSchema(parser.parse(new FileInputStream("avro/GeoIpCitySchema.avsc")));

            // TODO: Fix:
            // Exception in thread "main" org.apache.avro.message.BadHeaderException: Not enough header bytes
            //	at org.apache.avro.message.BinaryMessageDecoder.decode(BinaryMessageDecoder.java:149)
            //	at mayton.probeavro.geoip.GeoIpBinaryDecoder.main(GeoIpBinaryDecoder.java:28)
            while((geoIpCity = binaryMessageDecoder.decode(is, geoIpCity)) != null) {
                cnt++;
            }
            logger.info("cnt = {}", cnt); // expected = 5 748 952
            profiler.stop();
            TimeInstrument tm = profiler.stop();
            tm.print();

        } catch (IOException e) {
            logger.error("!", e);
        }
    }

}
