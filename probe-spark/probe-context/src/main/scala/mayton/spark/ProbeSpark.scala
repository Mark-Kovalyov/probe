package mayton.spark

import org.apache.spark.SparkConf

object ProbeSpark {

  def main(arg : Array[String]) : Unit = {
    val conf:SparkConf = new SparkConf().setAppName("Histogram").setMaster("local")
  }

}
