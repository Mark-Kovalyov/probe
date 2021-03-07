package mayton.probe.leveldb;

import org.iq80.leveldb.CompressionType;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.DbImpl;

import java.io.File;

public class ProbeLevelDb {

    public static void main(String[] args) throws Exception {

        File fileDir = new File("db/leveldb");

        Options options = new Options()
                .blockSize(4 * 1024)
                .cacheSize(256L * 1024 * 1024)
                .compressionType(CompressionType.NONE)
                .createIfMissing(true)
                .verifyChecksums(true)
                .writeBufferSize(4 << 20);

        DB db = new DbImpl(options, fileDir);

        db.put("key1".getBytes(), "value1".getBytes());

        db.close();
    }

}
