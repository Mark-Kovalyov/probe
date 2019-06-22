package probe.mayton.orc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.log4j.PropertyConfigurator;
import org.apache.orc.CompressionKind;
import org.apache.orc.OrcFile;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;

import java.util.Random;


public class Main {

    // https://orc.apache.org/docs/core-java.html

    public static void main(String[] args) throws Exception {

        PropertyConfigurator.configure("log4j.properties");

        Configuration conf = new Configuration();

        TypeDescription schema = TypeDescription.fromString("struct<x:int,y:int>");

        //FileSystem hadoopFileSystem = FileSystem.getLocal(conf);


        // ORC: 2015 Faster, Better, Smaller
        // - https://www.slideshare.net/Hadoop_Summit/orc-2015-faster-better-smaller

        Writer writer = OrcFile.createWriter(
                new Path("files/probe-lz4.orc"),
                OrcFile.writerOptions(conf)
                        .setSchema(schema)
                        .overwrite(true)
                        .compress(CompressionKind.LZ4) // { SNAPPY | ZLIB | NONE | LZO | LZ4 }
        );


        VectorizedRowBatch batch = schema.createRowBatch();
            LongColumnVector   x = (LongColumnVector) batch.cols[0];
            LongColumnVector   y = (LongColumnVector) batch.cols[1];
            //DoubleColumnVector z = (DoubleColumnVector) batch.cols[2];

        Random random = new Random();

        for(int r=0; r < 20_000; r++) {
            int row = batch.size++;
            x.vector[row] = r;
            y.vector[row] = r % 2;
            //z.vector[row] = 3.0 + random.nextGaussian();

            if (batch.size == batch.getMaxSize()) {
                writer.addRowBatch(batch);
                batch.reset();
            }
        }

        if (batch.size != 0) {
            writer.addRowBatch(batch);
            batch.reset();
        }

        writer.close();
    }
}
