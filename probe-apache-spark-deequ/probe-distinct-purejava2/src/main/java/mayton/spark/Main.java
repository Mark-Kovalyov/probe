package mayton.spark;

import org.apache.spark.sql.SparkSession;

public class Main{

    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("probe-distinct-purejava2")
                .getOrCreate();

        sparkSession.stop();
    }

}
