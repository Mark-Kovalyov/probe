package probe.mayton.orc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.CompressionKind;
import org.apache.orc.OrcFile;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;

import java.io.IOException;

public class OrcWriter2 {

    public static void main(String[] args) throws IOException,
            InterruptedException {

        String path = "files/orc-writer-2.orc";

        TypeDescription schema = TypeDescription.createStruct()
                .addField("field1", TypeDescription.createInt())
                .addField("field2", TypeDescription.createString())
                .addField("field3", TypeDescription.createString());

        Configuration conf = new Configuration();

        Writer writer = OrcFile.createWriter(new Path(path),
                OrcFile.writerOptions(conf)
                        .setSchema(schema)
                        .stripeSize(100000)
                        .bufferSize(10000)
                        .compress(CompressionKind.ZLIB)
                        .version(OrcFile.Version.V_0_12));

        VectorizedRowBatch batch = schema.createRowBatch();
        batch.size = 1;

        ((LongColumnVector) batch.cols[0]).vector[0] = 1;
        ((BytesColumnVector) batch.cols[1]).setVal(0, "hello".getBytes());
        ((BytesColumnVector) batch.cols[2]).setVal(0, "orcFile".getBytes());

        writer.addRowBatch(batch);
        writer.close();
    }

}
