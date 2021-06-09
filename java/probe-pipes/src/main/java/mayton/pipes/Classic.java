package mayton.pipes;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import mayton.geo.GeoIpEntity;
import mayton.network.NetworkUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.TimeInstrument;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

public class Classic {

    public static class GeoIpDummyEntity implements Serializable {
        public String beginIp;
        public String endIp;

        public GeoIpDummyEntity(String beginIp, String endIp) {
            this.beginIp = beginIp;
            this.endIp = endIp;
        }
    }

    static Logger logger = LoggerFactory.getLogger(Classic.class);

    static final int BUFFER_SIZE = 64 * 1024;

    // zless "/storage/db/GEO/maxmind/2010-10.MaxMind GeoIP City CSV Format/GeoIP-139_20101001/GeoIPCity.csv.gz"

    // ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    //        london.writeDelimitedTo(byteArrayOutputStream);
    //        london.writeDelimitedTo(byteArrayOutputStream);
    //        byteArrayOutputStream.flush();
    //        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    //        GeoIpEntity.GeoIpCity londonRecovered1 = GeoIpEntity.GeoIpCity.parseDelimitedFrom(byteArrayInputStream);
    //        GeoIpEntity.GeoIpCity londonRecovered2 = GeoIpEntity.GeoIpCity.parseDelimitedFrom(byteArrayInputStream);

    // TeeOutputStream teeOutputStream = new TeeOutputStream(new NullOutputStream(), new NullOutputStream());

    // CountingOutputStream countingOutputStream = new CountingOutputStream(new NullOutputStream());

    public static void main(String[] args) throws InterruptedException, IOException {

        // gzipInputStream::gzipDecoder::rawCsvOutput ->
        //     rawCsvInput(64k)::csvParserThread::protobufOutputStream ->
        //          protobufInputStream(64k)::jsonGeoWriter::

        Profiler profiler = new Profiler("Sample");
        profiler.start("Pipeline");

        InputStream gzipInputStream = new GZIPInputStream(new FileInputStream("/storage/db/GEO/maxmind/2010-10.MaxMind GeoIP City CSV Format/GeoIP-139_20101001/GeoIPCity.csv.gz"));

        PipedInputStream rawCsvInput = new PipedInputStream(BUFFER_SIZE);
        PipedOutputStream rawCsvOutput = new PipedOutputStream();
        rawCsvOutput.connect(rawCsvInput);

        Thread gzipDecoder = new Thread(() -> {
            try {
                int res = IOUtils.copy(gzipInputStream, rawCsvOutput);
                rawCsvOutput.close();
                logger.info("[1] copied {} bytes" , res);
            } catch (IOException e) {
                logger.error("", e);
            }
        });

        gzipDecoder.start();

        PipedInputStream  protobufInputStream = new PipedInputStream(BUFFER_SIZE);
        PipedOutputStream protobufOutputStream = new PipedOutputStream();
        protobufOutputStream.connect(protobufInputStream);

        Thread csvParserThread = new Thread(() -> {
            try {
                CSVParser parser = CSVParser.parse(rawCsvInput, StandardCharsets.UTF_8,
                        CSVFormat.DEFAULT
                                .withDelimiter(',')
                                .withFirstRecordAsHeader());

                Iterator<CSVRecord> iterator = parser.iterator();
                int cnt = 0;
                while(iterator.hasNext()) {
                    CSVRecord csvRecord = iterator.next();
                    GeoIpEntity.GeoIpCity geoIpEntity = GeoIpEntity.GeoIpCity.newBuilder()
                            .setStartIpNum((int) NetworkUtils.parseIpV4(csvRecord.get(0)))
                            .setEndIpNum((int) NetworkUtils.parseIpV4(csvRecord.get(1)))
                            .setCountry(csvRecord.get(2))
                            .setRegion(csvRecord.get(3))
                            .setCity(csvRecord.get(4))
                            .setLatitude(0.0)
                            .setLongitude(0.0)
                            .build();
                    geoIpEntity.writeDelimitedTo(protobufOutputStream);
                    cnt++;
                }
                protobufOutputStream.close();
                logger.info("[2] copied {} records" , cnt);
            } catch (IOException e) {
                logger.error("", e);
            }
        });

        csvParserThread.start();

        Thread jsonGeoWriter = new Thread(() -> {
            JsonFactory jfactory = new JsonFactory();
            int cnt = 0;
            try (OutputStream stream = new FileOutputStream("json.json")) {
                JsonGenerator jGenerator = jfactory.createGenerator(stream, JsonEncoding.UTF8)
                        .setPrettyPrinter(new DefaultPrettyPrinter());

                jGenerator.writeStartArray();
                boolean eof = false;
                do {
                    try {
                        GeoIpEntity.GeoIpCity entity = GeoIpEntity.GeoIpCity.parseDelimitedFrom(protobufInputStream);
                        jGenerator.writeStartObject();
                        jGenerator.writeNumberField("beginIp", entity.getStartIpNum());
                        jGenerator.writeNumberField("endIp", entity.getEndIpNum());
                        jGenerator.writeStringField("country", entity.getCountry());
                        jGenerator.writeStringField("region", entity.getRegion());
                        jGenerator.writeStringField("city", entity.getCity());
                        jGenerator.writeNumberField("lattitude", entity.getLatitude());
                        jGenerator.writeNumberField("longitude", entity.getLongitude());
                        jGenerator.writeEndObject();
                        cnt++;
                    } catch (IOException ex) {
                        jGenerator.writeEndArray();
                        eof = true;
                    }
                } while(!eof);
                logger.info("[3] read {} objects", cnt);
            } catch (EOFException eofException) {
                // Ignore it
                // jGenerator.writeEndArray();
                logger.info("[3] read {} objects (exit by EOF)", cnt);
            } catch (IOException e) {
                logger.error("", e);
            }
        });

        jsonGeoWriter.start();

        gzipDecoder.join();
        csvParserThread.join();
        jsonGeoWriter.join();

        long end = System.currentTimeMillis();

        TimeInstrument tm = profiler.stop();

        //printing the contents of the time instrument
        tm.print();

    }

}
