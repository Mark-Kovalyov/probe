package mayton.probeavro.geoip;

import mayton.network.NetworkUtils;
import org.apache.avro.Schema;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumWriter;
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

public class GeoIpSpecificDatumWriterExample {

    static Logger logger = LoggerFactory.getLogger(GeoIpSpecificDatumWriterExample.class);

    public static void schemaV1(String inputCsv, String outputAvro) {
        try (OutputStream outputStreamAvro = new FileOutputStream(outputAvro)) {
            CSVParser parser = CSVParser.parse(new FileInputStream(inputCsv), StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT
                            .withDelimiter(',')
                            .withFirstRecordAsHeader());
            Iterator<CSVRecord> irec = parser.iterator();
            //BinaryEncoder binaryEncoder = EncoderFactory.get().blockingBinaryEncoder(outputStreamAvro, null);

            BinaryEncoder directEncoder = EncoderFactory.get().directBinaryEncoder(outputStreamAvro, null);
            SpecificDatumWriter specificDatumWriter = new SpecificDatumWriter();
            specificDatumWriter.setSchema(GeoIpCityAvroEntityV1.getClassSchema());

            GeoIpCityAvroEntityV1 forReuse = new GeoIpCityAvroEntityV1();
            while (irec.hasNext()) {
                CSVRecord rec = irec.next();
                GeoIpCityAvroEntityV1 entity = GeoIpCityAvroEntityV1.newBuilder(forReuse)
                        .setStartIpNum((int) NetworkUtils.parseIpV4(rec.get(0)))
                        .setEndIpNum((int) NetworkUtils.parseIpV4(rec.get(1)))
                        .setCountry(rec.get(2))
                        .setRegion(rec.get(3))
                        .setCity(rec.get(4))
                        .build();
                specificDatumWriter.write(entity, directEncoder);
            }

            parser.close();
        } catch (IOException ex) {
            logger.error("!", ex);
        }
    }

    public static void schemaXwithEncoder(String inputCsv, String outputAvro, Schema schema, Encoder encoder) {

    }

    public static void schemaV2(String inputCsv, String outputAvro) {
        try (OutputStream outputStreamAvro = new FileOutputStream(outputAvro)) {
            CSVParser parser = CSVParser.parse(new FileInputStream(inputCsv), StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT
                            .withDelimiter(',')
                            .withFirstRecordAsHeader());
            Iterator<CSVRecord> irec = parser.iterator();
            GeoIpCityAvroEntityV2 forReuse = new GeoIpCityAvroEntityV2();
            SpecificDatumWriter specificDatumWriter = new SpecificDatumWriter(GeoIpCityAvroEntityV2.getClassSchema());
            //BinaryEncoder binaryEncoder = EncoderFactory.get().blockingBinaryEncoder(outputStreamAvro, null);
            JsonEncoder jsonEncoder = EncoderFactory.get().jsonEncoder(GeoIpCityAvroEntityV2.getClassSchema(),outputStreamAvro,true);
            while (irec.hasNext()) {
                CSVRecord rec = irec.next();
                GeoIpCityAvroEntityV2 entity = GeoIpCityAvroEntityV2.newBuilder(forReuse)
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
                specificDatumWriter.write(entity, jsonEncoder);
            }
            parser.close();
        } catch (IOException ex) {
            logger.error("!", ex);
        }
    }

    public static void main(String[] args) throws Exception {
        Profiler profiler = new Profiler("Avro BinaryEncoder");
        profiler.start("GeoIPCity-v1.avro");
        //schemaV1("/bigdata/GeoIPCity.utf-8.csv", "/bigdata/GeoIPCity-v1-directBinaryEncoder.avro");
        profiler.start("GeoIPCity-v2.avro");
        schemaV2("/bigdata/GeoIPCity.utf-8.csv", "/bigdata/GeoIPCity-v2.json");
        profiler.stop();
        TimeInstrument tm = profiler.stop();
        tm.print();
    }

}
