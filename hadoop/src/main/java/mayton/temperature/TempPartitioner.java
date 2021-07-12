package mayton.temperature;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;

public class TempPartitioner implements Partitioner<LongWritable, Text> {

    @Override
    public int getPartition(LongWritable key, Text value, int numPartitions) {
        // This is just default implementation from hash partitioner
        return (key.hashCode() & Integer.MAX_VALUE) % numPartitions;
    }

    @Override
    public void configure(JobConf job) {

    }
}
