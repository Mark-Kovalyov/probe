package mayton.probeavro.geoip;

import org.apache.avro.message.BinaryMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.TimeInstrument;

import java.io.*;

public class GeoIpSpecificDatumReaderExample {

    static Logger logger = LoggerFactory.getLogger(GeoIpSpecificDatumReaderExample.class);

    public static void main(String[] args) {

        Profiler profiler = new Profiler("GeoIpBinaryDecoder");

        profiler.start("Reading from binary avro");
        int cnt = 0;
        try(InputStream is = new FileInputStream("dat/geo-ip-entity.avro")) {

            GeoIpCityAvroEntityV2 geoIpCity = new GeoIpCityAvroEntityV2();

            logger.info("cnt = {}", cnt); // expected = 5 748 952
            profiler.stop();
            TimeInstrument tm = profiler.stop();
            tm.print();

        } catch (IOException e) {
            logger.error("!", e);
        }
    }

}
