package mayton.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Column, DataFrame, DataFrameReader, Dataset, SparkSession}
import org.apache.spark.{HashPartitioner, Partitioner, SparkContext}
import org.apache.spark.storage.StorageLevel

/**
 * Sample:
 *
 * startIpNum,endIpNum,country,region,city,postalCode,latitude,longitude,dmaCode,areaCode
 * 1.0.0.0,1.7.255.255,"AU","","","",-27.0000,133.0000,,
 * 1.9.0.0,1.9.255.255,"MY","","","",2.5000,112.5000,,
 * 1.10.10.0,1.10.10.255,"AU","","","",-27.0000,133.0000,,
 * 1.11.0.0,1.11.255.255,"KR","","","",37.0000,127.5000,,
 * 1.12.0.0,1.15.255.255,"CN","","","",35.0000,105.0000,,
 * 1.16.0.0,1.19.255.255,"KR","","","",37.0000,127.5000,,
 * 1.21.0.0,1.21.255.255,"JP","","","",36.0000,138.0000,,
 **/
object GeoIpDistinct {

  def main(args : Array[String]): Unit = {
    // Create a SparkSession. No need to create SparkContext
    // You automatically get it as part of the SparkSession
    val warehouseLocation = "file:///bigdata/probe-distinct-scala"

    //.enableHiveSupport()
    val spark : SparkSession = SparkSession.builder()
        .appName("probe-distinct-scala")
        .config("spark.sql.warehouse.dir", warehouseLocation)
        .getOrCreate()

    val sc : SparkContext = spark.sparkContext


    // -------------------------- Start script here: ---------------------------------------------------------------

    /*
    import org.apache.spark.sql.{Column, DataFrame, DataFrameReader, Dataset, SparkSession}
    import org.apache.spark.{HashPartitioner, Partitioner, SparkContext}
    import org.apache.spark.storage.StorageLevel
     */
    val dataFrameReader : DataFrameReader = spark.read.format("csv").option("header", "true").option("delimiter", ",")

    // EQ_Metadata*.csv
    val geoCity : DataFrame = dataFrameReader.load("file:///bigdata/GeoIPCity.utf-8.csv")

    // Count distinct of "country"

    val hashPart : Partitioner = new HashPartitioner(2)

    //val myRDD = geoCity.distinct().partitionBy(hashPart).setName("geo-ip-country").persist(StorageLevel.DISK_ONLY)
    // TODO: How to get from name? Or index from name?
    val column : Column = geoCity("country")

    val countryColumn : Dataset[String] = geoCity.map(row => row.getString(2))

    countryColumn.persist(StorageLevel.DISK_ONLY)

    val distinctCountryColumn = countryColumn.distinct()

    distinctCountryColumn.persist(StorageLevel.DISK_ONLY)

    // Show first 20 values
    distinctCountryColumn.show(20, false)

    val count = distinctCountryColumn.count()

    // TODO: SparkException: Checkpoint directory has not been set in the SparkContext
    //distinctCountryColumn.checkpoint()

    println(count)

    spark.stop()
  }

}
