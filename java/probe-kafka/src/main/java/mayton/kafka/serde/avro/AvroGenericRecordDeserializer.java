package mayton.kafka.serde.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AvroGenericRecordDeserializer implements Serializer<List<GenericRecord>> {

    private Schema schema = null;

    public static Logger logger = LoggerFactory.getLogger(AvroGenericRecordDeserializer.class);

    @Override
    public void configure(Map<String, ?> map, boolean isKey) {
        schema = (Schema) map.get("SCHEMA");
    }

    @Override
    public byte[] serialize(String topic, List<GenericRecord> records) {
        byte[] retVal = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        GenericDatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        DataFileWriter dataFileWriter = new DataFileWriter<>(datumWriter);
        try {
            dataFileWriter.create(schema, outputStream);
            for (GenericRecord record : records) {
                dataFileWriter.append(record);
            }
            dataFileWriter.flush();
            dataFileWriter.close();
            retVal = outputStream.toByteArray();
        } catch (IOException e) {
            logger.error("!",e);
        }
        return retVal;
    }
}
