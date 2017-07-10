package mayton.probeavro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Hello world!
 */
public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public App() throws IOException {

        logger.info("::begin");

        logger.info("::configure schema");

        Schema.Parser parser = new Schema.Parser();

        Schema schema = parser.parse(new FileInputStream("schema/StringPair.avsc"));
        /////
        logger.info("::populate record");

        GenericRecord datum = new GenericData.Record(schema);
        datum.put("left","1");
        datum.put("right","2");

        logger.info("::write");

        OutputStream outStream = new FileOutputStream("tmp/out1.avro");
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
        Encoder encoder = EncoderFactory.get().binaryEncoder(outStream, null);
        writer.write(datum, encoder);
        encoder.flush();
        outStream.close();

        logger.info("::read");

        DatumReader<GenericRecord> reader = new GenericDatumReader<>();
        InputStream inStream = new FileInputStream("tmp/out1.avro");

        Decoder decoder = DecoderFactory.get().binaryDecoder(inStream, null);

        GenericRecord result = reader.read(null, decoder);

        logger.info(":: left = {}", result.get("left").toString());
        logger.info(":: right = {}", result.get("right").toString());

        logger.info("::end");
    }

    public static void main(String[] args) throws IOException {
        new App();
    }
}
