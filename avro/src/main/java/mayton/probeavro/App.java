package mayton.probeavro;

import org.apache.avro.Schema;
import org.apache.avro.data.Json;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.log4j.BasicConfigurator;
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
public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public App() throws IOException {

        BasicConfigurator.configure();

        logger.info("::configure schema");

        Schema.Parser parser = new Schema.Parser();

        Schema schema = parser.parse(new FileInputStream("schema/StringPair.avsc"));
        /////
        logger.info("::populate record");

        GenericRecord datum = new GenericData.Record(schema);
        datum.put("left","1");
        datum.put("right","2");

        logger.info("::write");

        OutputStream outStream = new FileOutputStream("target/datum-out1.avro");
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
        Encoder encoder = EncoderFactory.get().binaryEncoder(outStream, null);
        writer.write(datum, encoder);
        encoder.flush();
        outStream.close();

        logger.info("::read");

        DatumReader<GenericRecord> reader = new GenericDatumReader<>(schema);

        InputStream inStream = new FileInputStream("target/out1.avro");

        Decoder decoder = DecoderFactory.get().binaryDecoder(inStream, null);

        GenericRecord result = reader.read(null, decoder);

        logger.info(":: left = {}", result.get("left").toString());
        logger.info(":: right = {}", result.get("right").toString());

        logger.info("::end");

        // TODO:
        OutputStream jsonStream = new FileOutputStream("target/json-out1.avro");
        Json.ObjectWriter objectWriter = new Json.ObjectWriter();

        Schema schema2 = parser.parse(new FileInputStream("schema/StringPair.avsc"));

        Encoder jsonEncoder = EncoderFactory.get().jsonEncoder(schema2, jsonStream);

        objectWriter.write(new GeoIpCity(), jsonEncoder);

    }

    public static void main(String[] args) throws IOException {
        new App();
    }
}
