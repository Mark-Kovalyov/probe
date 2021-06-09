package mayton.pipes;

import mayton.geo.GeoIpEntity;
import mayton.network.NetworkUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.TimeInstrument;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

public class Synchro {

    public static void main(String[] args) throws IOException {

        Profiler profiler = new Profiler("Sample");
        profiler.start("Read gzip/csv, generate protobuf");

        InputStream gzipInputStream = new GZIPInputStream(new FileInputStream(Constants.SRC));

        CSVParser parser = CSVParser.parse(gzipInputStream, StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT
                            .withDelimiter(',')
                            .withFirstRecordAsHeader());

        OutputStream protobufOutputStream = new FileOutputStream("GeoIpCity.protobuf");

        Iterator<CSVRecord> iterator = parser.iterator();
        int cnt = 0;

        // startIpNum,endIpNum,    country,region,city,postalCode,latitude,longitude,dmaCode,areaCode
        // 1.0.0.0,   1.7.255.255,"AU",    "",    "",  "",        -27.0000,133.0000,,
        while (iterator.hasNext()) {
            CSVRecord csvRecord = iterator.next();
            GeoIpEntity.GeoIpCity geoIpEntity = GeoIpEntity.GeoIpCity.newBuilder()
                    .setStartIpNum((int) NetworkUtils.parseIpV4(csvRecord.get(0)))
                    .setEndIpNum((int) NetworkUtils.parseIpV4(csvRecord.get(1)))
                    .setCountry(csvRecord.get(2))
                    .setRegion(csvRecord.get(3))
                    .setCity(csvRecord.get(4))
                    .setPostalCode(csvRecord.get(5))
                    .setLatitude(Double.parseDouble(csvRecord.get(6)))
                    .setLongitude(Double.parseDouble(csvRecord.get(7)))
                    .setDmaCode(csvRecord.get(8))
                    .setAreaCode(csvRecord.get(9))
                    .build();
            geoIpEntity.writeDelimitedTo(protobufOutputStream);
            cnt++;
        }
        protobufOutputStream.close();
        gzipInputStream.close();

        TimeInstrument tm = profiler.stop();

        //printing the contents of the time instrument
        tm.print();

    }

}
