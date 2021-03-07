package mayton.probe;

import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import java.util.List;

public class CustomParquetWriter extends ParquetWriter<List<String>> {

    public CustomParquetWriter(
            Path file,
            MessageType schema,
            boolean enableDictionary,
            CompressionCodecName codecName
    ) throws IOException {
        // OutputFile file, Mode mode, WriteSupport<T> writeSupport, CompressionCodecName compressionCodecName, int rowGroupSize, boolean validating, Configuration conf, int maxPaddingSize, ParquetProperties encodingProps
        super(file, new CustomWriteSupport(schema), codecName, DEFAULT_BLOCK_SIZE, DEFAULT_PAGE_SIZE, enableDictionary, false);
    }
}
