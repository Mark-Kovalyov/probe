package mayton.spark
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Column, DataFrame, DataFrameReader, Dataset, SparkSession}
import org.apache.spark.{HashPartitioner, Partitioner, SparkContext}
import org.apache.spark.storage.StorageLevel
import java.net.URI
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.hadoop.conf.Configuration


object GeoIpMultipleFiles {

  def main(args : Array[String]): Unit = {

    val warehouseLocation = "file:///bigdata/probe-distinct-scala"

    val spark : SparkSession = SparkSession.builder()
      .appName("probe-distinct-scala")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .getOrCreate()

    val sc : SparkContext = spark.sparkContext

    // -------------------------- Start script here: ---------------------------------------------------------------

    sc.parallelize(Seq[String] )

    val dataFrameReader : DataFrameReader = spark.read.format("csv").option("header", "true").option("delimiter", ",")

    val path = "s3://mtn-df/somefolder"
    val fileSystem = FileSystem.get(URI.create(path), spark.conf)
    val it = fileSystem.listFiles(new Path(path), true)
    while (it.hasNext()) {
      ...
    }


    spark.stop()
  }

}
