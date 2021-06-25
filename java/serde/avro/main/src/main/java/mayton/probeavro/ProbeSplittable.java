package mayton.probeavro;

import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.commons.io.output.NullOutputStream;

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

        //   * Set the synchronization interval for this file, in bytes. Valid values range
        //   * from 32 to 2^30 Suggested values are between 2K and 2M
        //   *
        //   * The stream is flushed by default at the end of each synchronization interval.
        //   *
        //   * If {@linkplain #setFlushOnEveryBlock(boolean)} is called with param set to
        //   * false, then the block may not be flushed to the stream after the sync marker
        //   * is written. In this case, the {@linkplain #flush()} must be called to flush
        //   * the stream.
        dataFileWriter.setSyncInterval(2 * 1024 * 1024);
        dataFileWriter.setCodec(CodecFactory.bzip2Codec());

        Encoder binaryEncoder = EncoderFactory.get().binaryEncoder(NullOutputStream.nullOutputStream(), null);

        Encoder jsonEncoder = EncoderFactory.get().jsonEncoder(schema, NullOutputStream.nullOutputStream());




    }

}
