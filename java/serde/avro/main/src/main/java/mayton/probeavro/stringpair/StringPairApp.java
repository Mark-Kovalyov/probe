package mayton.probeavro.stringpair;

import mayton.probeavro.geoip.GeoIpCity;
import org.apache.avro.Schema;
import org.apache.avro.data.Json;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * See : DatumWriter
 *       SpecificDatumWriter
 *       GenericDatumWriter
 *       Json.Writer, ProtobufDatumWriter, ReflectDatumWriter, ThriftDatumWriter,
 *       Encoder:
 *       EncoderFactory *
 *       DatumReader
 *       GenericDatumReader
 *       Json.ObjectReader, Json.Reader, ProtobufDatumReader, ReflectDatumReader, ThriftDatumReader.
 *
 *       Decoder:
 */
public class StringPairApp {

    static Logger logger = LoggerFactory.getLogger(StringPairApp.class);

    public StringPairApp() throws IOException {


        logger.info("::configure schema");

        Schema.Parser parser = new Schema.Parser();

        Schema schema = parser.parse(new FileInputStream("avro/StringPair.avsc"));
        /////
        logger.info("::populate record");

        GenericRecord datum = new GenericData.Record(schema);
        datum.put("left","leftvalue");
        datum.put("right","rightvalue");

        logger.info("::write");

        OutputStream outStream = new FileOutputStream("dat/StringPair-01.avro");
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
        Encoder encoder = EncoderFactory.get().binaryEncoder(outStream, null);
        writer.write(datum, encoder);
        encoder.flush();
        outStream.close();

        logger.info("::read");

        DatumReader<GenericRecord> reader = new GenericDatumReader<>(schema);

        InputStream inStream = new FileInputStream("dat/StringPair-01.avro");

        Decoder decoder = DecoderFactory.get().binaryDecoder(inStream, null);

        GenericRecord result = reader.read(null, decoder);

        logger.info(":: left = {}", result.get("left").toString());
        logger.info(":: right = {}", result.get("right").toString());

        try(OutputStream jsonStream = new FileOutputStream("dat/StringPair-01.json")) {
            Json.ObjectWriter objectWriter = new Json.ObjectWriter();
            Encoder jsonEncoder = EncoderFactory.get().jsonEncoder(schema, jsonStream);
            objectWriter.setSchema(schema);
            objectWriter.write(new GeoIpCity(), jsonEncoder);

        } catch (IOException ex) {
            logger.error("!",ex);
        }

        logger.info("::end");

    }

    public static void main(String[] args) throws IOException {
        new StringPairApp();
    }
}
