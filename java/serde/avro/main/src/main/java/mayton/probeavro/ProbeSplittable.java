package mayton.probeavro;

import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;

import java.io.FileInputStream;
import java.io.IOException;

public class ProbeSplittable {


    public static void main(String[] args) throws IOException {

        // Configure Splittable markers and BZip2 compression

        Schema.Parser parser = new Schema.Parser();

        Schema schema = parser.parse(new FileInputStream("avro/StringPair.avsc"));

        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);

        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);

        dataFileWriter.setFlushOnEveryBlock(true);
        dataFileWriter.setSyncInterval(2 * 1024 * 1024);
        dataFileWriter.setCodec(CodecFactory.bzip2Codec());




    }

}
