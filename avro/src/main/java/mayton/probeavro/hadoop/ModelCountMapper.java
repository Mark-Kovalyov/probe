package mayton.probeavro.hadoop;

import com.example.avroSample.model.Automobile;
import org.apache.avro.mapred.AvroCollector;
import org.apache.avro.mapred.AvroMapper;
import org.apache.avro.mapred.Pair;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

public class ModelCountMapper extends AvroMapper<Automobile, Pair<CharSequence, Integer>> {

    @Override
    public void map(Automobile datum, AvroCollector<Pair<CharSequence, Integer>> collector, Reporter reporter) throws IOException {
        // TODO:
    }
}
