package mayton.spark

import org.apache.spark.{HashPartitioner, Partitioner, SparkContext}

class GeoIpPartitioner extends Partitioner {
  override def numPartitions: Int = ???

  override def getPartition(key: Any): Int = {
    0
  }
}
