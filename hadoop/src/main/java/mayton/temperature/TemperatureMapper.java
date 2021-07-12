package mayton.temperature;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

public class TemperatureMapper implements Mapper <LongWritable, Text, Text, IntWritable> {


    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        // TODO: Fix
        output.collect(new Text(""), new IntWritable(1));
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void configure(JobConf job) {

    }


}
