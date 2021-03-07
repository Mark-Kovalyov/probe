package mayton.probeavro.hadoop;

import org.apache.avro.mapred.AvroCollector;
import org.apache.avro.mapred.AvroReducer;
import org.apache.avro.mapred.Pair;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

public class ModelCountReducer extends AvroReducer<CharSequence, Integer, Pair<CharSequence, Integer>> {

    @Override
    public void reduce(CharSequence key, Iterable<Integer> values, AvroCollector<Pair<CharSequence, Integer>> collector, Reporter reporter) throws IOException {
        // TODO
    }
}
