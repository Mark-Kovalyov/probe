import org.apache.spark.{SparkConf, SparkContext}

object Main {

    def main(arg : Array[String]): Unit = {

        val conf = new SparkConf().setMaster("local").setAppName("My App")

        val sc = new SparkContext(conf)

        val input = sc.textFile("/db/maven/mvnidx.csv")

        val words = input.flatMap(line => line.split(" "))

        val counts = words.map(words => (words, 1)).reduceByKey( {case (x,y) => x + y} )

        counts.saveAsTextFile("/db/maven/mvnidx_report.txt")

        printf("OK")

    }

}
