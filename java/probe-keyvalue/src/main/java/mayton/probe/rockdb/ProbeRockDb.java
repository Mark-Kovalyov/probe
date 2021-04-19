package mayton.probe.rockdb;

import org.rocksdb.*;

import java.io.File;

public class ProbeRockDb {

    public static void main(String[] args) throws RocksDBException {

        for(File f : new File("/storage/fias/fias_dbf").listFiles()) {
            if (!f.isDirectory()) {
                String fileName = f.getName();
                if (fileName.endsWith(".DBF")) {
                    System.out.println(fileName);
                }
            }
        }

        Options options = new Options()
                .setCreateIfMissing(true);



        String dbPath = "db/rocksdb/data";

        RocksDB db = RocksDB.open(options, dbPath);

        db.put("key".getBytes(),"value".getBytes());

       /* ColumnFamilyHandle handle;

        db.put(handle, "key".getBytes(),"value".getBytes());*/

        db.close();


    }

}
