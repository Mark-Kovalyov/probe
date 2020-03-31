package mayton.spark

import java.io.{FileWriter, PrintWriter}

import mayton.spark.GeoIpOrc.checkForUnique
import org.apache.log4j.{BasicConfigurator, Level, LogManager}
import org.apache.spark.sql.{DataFrame, DataFrameReader, SparkSession}

object GeoIpOrcDeequ {

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

    

    log.info(":: Finish GeoIpOrc")
  }


}
