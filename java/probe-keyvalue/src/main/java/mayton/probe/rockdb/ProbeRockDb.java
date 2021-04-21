package mayton.probe.rockdb;

import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import org.rocksdb.*;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ProbeRockDb {

    public static String formatType(Class clazz) {
        String s = clazz.getName();
        if (s.startsWith("java.lang.")) return s.substring("java.lang.".length());
        if (s.startsWith("java.util.")) return s.substring("java.util.".length());
        return clazz.getName();
    }

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatObj(Object obj) {
        if (obj instanceof java.util.Date) return simpleDateFormat.format(obj);
        return obj.toString();
    }

    public static void main(String[] args) throws RocksDBException, FileNotFoundException {

        Options options = new Options().setCreateIfMissing(true);

        String dbPath = "db";

        new File(dbPath).mkdirs();

        RocksDB db = RocksDB.open(options, dbPath);


        for(File f : new File("/storage/fias/fias_dbf").listFiles()) {
            if (!f.isDirectory()) {
                String fileName = f.getName();
                if (fileName.endsWith(".DBF")) {
                    System.out.println(fileName);
                    DBFReader reader = new DBFReader(new FileInputStream(f.getAbsolutePath()));
                    int numberOfFields = reader.getFieldCount();
                    for (int i = 0; i < numberOfFields; i++) {
                        DBFField field = reader.getField(i);
                        System.out.print(field.getName());
                        System.out.print(" ");
                    }

                    Object[] rowObjects;
                    int rownum = 0;
                    while ((rowObjects = reader.nextRecord()) != null) {
                        for (int i = 0; i < rowObjects.length; i++) {
                            if (rowObjects[i] != null) {
                                System.out.printf("[%d] '%s' (%s)\n",
                                        i,
                                        formatObj(rowObjects[i]),
                                        formatType(rowObjects[i].getClass()));
                            } else {
                                System.out.printf(" null\n");
                            }
                        }
                        rownum++;
                        System.out.println();
                        if (rownum > 10) break;
                    }
                    reader.close();
                }
            }
        }


        db.put("key".getBytes(),"value".getBytes());

       /* ColumnFamilyHandle handle;

        db.put(handle, "key".getBytes(),"value".getBytes());*/

        db.close();


    }

}
