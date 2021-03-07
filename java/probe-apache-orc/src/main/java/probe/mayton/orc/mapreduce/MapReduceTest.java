package probe.mayton.orc.mapreduce;

import org.apache.orc.RecordReader;
import org.apache.orc.TypeDescription;
import org.apache.orc.mapred.OrcMapredRecordReader;

import static probe.mayton.orc.schemas.SchemaStorage.maxMindSchema;

public class MapReduceTest {

    public static void main(String[] args) {

        RecordReader reader;

        OrcMapredRecordReader recordReader = new OrcMapredRecordReader(reader, maxMindSchema);



    }

}
