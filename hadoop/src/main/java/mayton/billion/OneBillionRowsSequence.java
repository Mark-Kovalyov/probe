package mayton.billion;

import mayton.lib.SofarTracker;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class OneBillionRowsSequence {

    public static String SRC = "/storage/db/1.2billion/1.2billion.txt.gz";
    public static String DEST = "/bigdata/billion/1.2billion.sequence";

    public static void main(String[] args) throws IOException {

        Configuration readConfig = new Configuration();

        SequenceFile.Writer.Option file       = SequenceFile.Writer.file(new Path(DEST));
        SequenceFile.Writer.Option compr      = SequenceFile.Writer.compression(SequenceFile.CompressionType.BLOCK);
        SequenceFile.Writer.Option keyClass   = SequenceFile.Writer.keyClass(Text.class);
        SequenceFile.Writer.Option valueClass = SequenceFile.Writer.valueClass(NullWritable.class);
        SequenceFile.Writer.Option bs         = SequenceFile.Writer.blockSize(4096);
        SequenceFile.Writer.Option append     = SequenceFile.Writer.appendIfExists(false);
        SequenceFile.Writer.Option interv     = SequenceFile.Writer.syncInterval(4096);

        SequenceFile.Writer writer = SequenceFile.createWriter(readConfig, file, compr, keyClass, valueClass, bs, append, interv);

        InputStream inputStream = new FileInputStream(SRC);
        CountingInputStream cis = new CountingInputStream(inputStream);
        GZIPInputStream gzipInputStream = new GZIPInputStream(cis);
        InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream, "utf-8");
        SingleCSVRowParser singleCSVRowParser = new SingleCSVRowParser(inputStreamReader);
        long cnt = 0;
        // $ lzop -l 1.2billion.txt.lzo
        // method      compressed  uncompr. ratio uncompressed_name
        // LZO1X-1     13299599987 14437813070  92.1% 1.2billion.txt
        SofarTracker bytesTracker = SofarTracker.createFileSizeTracker(14_437_813_070L);
        Text key = new Text();
        NullWritable value = NullWritable.get();
        while (singleCSVRowParser.hasNext()) {
            String res = singleCSVRowParser.next();
            key.set(res);
            cnt++;
            if ((cnt & 0xF_FFFFL) == 0xF_FFFFL) {
                bytesTracker.update((long) cis.getByteCount());
                System.out.println(bytesTracker);
            }
            writer.append(key, value);
        }
        bytesTracker.finish();
    }

}
