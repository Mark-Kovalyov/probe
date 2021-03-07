package mayton.probe;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.schema.MessageType;

import java.util.List;

public class CustomWriteSupport extends WriteSupport<List<String>> {
    public CustomWriteSupport(MessageType schema) {
    }

    @Override
    public WriteContext init(Configuration configuration) {
        return null;
    }

    @Override
    public void prepareForWrite(RecordConsumer recordConsumer) {

    }

    @Override
    public void write(List<String> record) {

    }
}
