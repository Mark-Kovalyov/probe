package mayton.geo;

import com.google.protobuf.CodedOutputStream;
import mayton.network.NetworkUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.TimeInstrument;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class Main {

    static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        Profiler profiler = new Profiler("GeoIpBinaryEncoder");

        profiler.start("Export to binary protobuf");

        int cnt = 0;


        try(OutputStream outputStreamProtobuf = new FileOutputStream("/bigdata/GeoIPCity-protobuf.dat")) {

            CSVParser parser = CSVParser.parse(new FileInputStream("/bigdata/GeoIPCity.utf-8.csv"), StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT
                            .withDelimiter(',')
                            .withFirstRecordAsHeader());

            Iterator<CSVRecord> irec = parser.iterator();

            while (irec.hasNext()) {

                CSVRecord rec = irec.next();
                int startIpNum = (int) NetworkUtils.parseIpV4(rec.get(0));
                int endIpNum = (int) NetworkUtils.parseIpV4(rec.get(1));

                GeoIpEntity.GeoIpCity entity = GeoIpEntity.GeoIpCity.newBuilder()
                        .setStartIpNum(startIpNum)
                        .setEndIpNum(endIpNum)
                        .setCity(rec.get(2))
                        .setCountry(rec.get(3))
                        .setAreaCode(rec.get(4))
                        .setDmaCode(rec.get(5))
                        .setLatitude(Double.parseDouble(rec.get(6)))
                        .setLongitude(Double.parseDouble(rec.get(7)))
                        .setPostalCode(rec.get(8))
                        .build();

                entity.writeDelimitedTo(outputStreamProtobuf);
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
