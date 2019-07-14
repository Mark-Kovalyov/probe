package mayton.probe.rockdb;

import org.rocksdb.ColumnFamilyHandle;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class ProbeRockDb {

    public static void main(String[] args) throws RocksDBException {

        Options options = new Options().setCreateIfMissing(true);

        String dbPath = "db/rocksdb";

        RocksDB db = RocksDB.open(options, dbPath);

        db.put("key".getBytes(),"value".getBytes());

       /* ColumnFamilyHandle handle;

        db.put(handle, "key".getBytes(),"value".getBytes());*/

        db.close();
    }

}
