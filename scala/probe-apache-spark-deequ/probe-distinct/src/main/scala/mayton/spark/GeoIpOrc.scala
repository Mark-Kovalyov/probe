package mayton.spark

import java.io.{FileWriter, PrintWriter}
import org.apache.log4j.{BasicConfigurator, Level, LogManager}
import org.apache.spark.sql.{DataFrame, DataFrameReader, Row, SparkSession}

/**
 * Since Spark 2.3, Spark supports a vectorized ORC reader with a new ORC file format for ORC files. To do that,
 * the following configurations are newly added. The vectorized reader is used for the native ORC tables (e.g.,
 * the ones created using the clause USING ORC) when spark.sql.orc.impl is set to native and
 * spark.sql.orc.enableVectorizedReader is set to true. For the Hive ORC serde tables (e.g., the ones
 * created using the clause USING HIVE OPTIONS (fileFormat 'ORC')), the vectorized reader is used
 * when spark.sql.hive.convertMetastoreOrc is also set to true.
 *
 *
 *
 */
object GeoIpOrc {

  def checkForUnique(dataFrame: DataFrame, columnName: String): Boolean = {
    dataFrame.select(columnName).distinct().count() == dataFrame.count()
  }

  def countColumns(dataFrame: DataFrame, columnName: String): Long = {
    dataFrame.select(columnName).filter(value => value == null).count()
  }

  def main(args: Array[String]): Unit = {

    BasicConfigurator.configure()

    val log = LogManager.getRootLogger
    log.setLevel(Level.INFO)

    log.info(":: Start GeoIpOrc")

    val warehouseLocation = "file:///bigdata/probe-orc"

    val spark: SparkSession = SparkSession.builder()
      .appName("probe-orc")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .config("spark.sql.orc.enableVectorizedReader", "true")
      .config("spark.sql.hive.convertMetastoreOrc", "true")
      .enableHiveSupport()
      .getOrCreate()

    val orcReader: DataFrameReader = spark.read.format("orc")

    val geoIPCityDataFrame: DataFrame = orcReader.load("/bigdata/GeoIPCity.orc")

    val count: Long = geoIPCityDataFrame.count()

    printf("Count = %s", count)

    if (!checkForUnique(geoIPCityDataFrame, "country")) {
      print("Non unique country!")
    }

    spark.stop()

    val printWriter: PrintWriter = new PrintWriter(new FileWriter(warehouseLocation + "/report.txt"))
    printWriter.println("Count = %s", count)
    printWriter.close()

    log.info(":: Finish GeoIpOrc")
  }

}
