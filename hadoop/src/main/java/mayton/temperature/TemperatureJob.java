package mayton.temperature;

//import org.apache.hadoop.mapred.jobcontrol.Job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;

public class TemperatureJob {

    public static void main(String[] args) throws IOException {
        JobConf jobConf = new JobConf();
        jobConf.setJobName("Max Temp");
        jobConf.setJarByClass(TemperatureJob.class);

        FileInputFormat.addInputPath(jobConf, new Path(args[0]));
        FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));

        jobConf.setMapperClass(TemperatureMapper.class);
        jobConf.setReducerClass(TemperatureReducer.class);

        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(IntWritable.class);

    }

}
