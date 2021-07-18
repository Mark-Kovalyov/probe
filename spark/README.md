# Spark + Deequu

**Keywords:SPARK,DEEQU,ORC,RDD**

## General Links

**Spark** (org.apache.spark) https://spark.apache.org/

Apache Spark is a fast and general-purpose cluster computing system. 
It provides high-level APIs in Java, Scala, Python and R, and an 
optimized engine that supports general execution graphs. 
It also supports a rich set of higher-level tools including 
Spark SQL for SQL and structured data processing, MLlib 
for machine learning, GraphX for graph processing, and Spark Streaming.

- Spark SQL http://spark.apache.org/docs/latest/sql-programming-guide.html
- MLIB http://spark.apache.org/docs/latest/ml-guide.html
- GraphX http://spark.apache.org/docs/latest/graphx-programming-guide.html
- Spark Streaming http://spark.apache.org/docs/latest/streaming-programming-guide.html 

**ORC** (org.apache.orc) https://orc.apache.org/  

**Spark + ORC** https://spark.apache.org/docs/latest/sql-data-sources-orc.html

**Deequu** https://aws.amazon.com/blogs/big-data/test-data-quality-at-scale-with-deequ/

**Hadoop** https://hadoop.apache.org/

**YARN** https://yarnpkg.com/

**Ganglia** (monitoring system) http://ganglia.info/ 

**Zeppelin** (interactive bigdata anlyze (like Athena?)) https://zeppelin.apache.org/

## Sources

- https://github.com/awslabs/deequ    
- https://github.com/apache/spark
- https://github.com/apache/orc

# Types

- org.apache.spark.sql.DataFrame
- org.apache.spark.sql.SparkSession
- org.apache.spark.sql.Row
- org.apache.spark.sql.Dataset
- org.apache.spark.sql.DataFrameWriter
- org.apache.hadoop.hive.ql.io.orc.OrcInputFormat

## Spark-shell

Display type:
```
scala> :type -v sc
// Type signature
org.apache.spark.SparkContext

// Internal Type structure
TypeRef(TypeSymbol(class SparkContext extends Logging))
```

## Spark Session

Already defined since 2.0
```
scala> :type spark
org.apache.spark.sql.SparkSession

scala> :type spark.sparkContext
org.apache.spark.SparkContext

scala> :type spark.sqlContext
org.apache.spark.sql.SQLContext
```

## Web UI


## Create a Cluster With Spark

https://docs.aws.amazon.com/emr/latest/ReleaseGuide/emr-spark-launch.html

```
aws emr create-cluster --name "Spark cluster" --release-label emr-5.29.0 --applications Name=Spark \
 --ec2-attributes KeyName=myKey --instance-type m5.xlarge --instance-count 3 --use-default-roles
```

### Other options:

Spark: Spark 2.4.4 on Hadoop 2.8.5 Yarn with Ganglia 3.7.2 and Zeppelin 0.8.2

## Configure Spark

https://docs.aws.amazon.com/emr/latest/ReleaseGuide/emr-spark-configure.html

### Instances Available

|Instance |
|---------|
|m5.xlarge|

## Amazon EMR console

https://console.aws.amazon.com/elasticmapreduce/

## Spark CLI
Must be Java-8
```
$ java -version
java version "1.8.0_212"
Java(TM) SE Runtime Environment (build 1.8.0_212-b10)
Java HotSpot(TM) 64-Bit Server VM (build 25.212-b10, mixed mode)
```

## Shell (+4g)

Retieve RDD from text file
```
$ ./spark-shell --master "local[4]" --driver-memory 4G

scala> val inputFile = sc.textFile("/bigdata/GeoIPCity.utf-8.csv")
inputFile: org.apache.spark.rdd.RDD[String] = /bigdata/GeoIPCity.utf-8.csv MapPartitionsRDD[1] at textFile at <console>:24
```

Count words:
```
scala> val counts = inputFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
counts: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[4] at reduceByKey at <console>:25
```

Debug execution plan
```
scala> counts.toDebugString
res1: String =
(13) ShuffledRDD[4] at reduceByKey at <console>:25 []
 +-(13) MapPartitionsRDD[3] at map at <console>:25 []
    |   MapPartitionsRDD[2] at flatMap at <console>:25 []
    |   /bigdata/GeoIPCity.utf-8.csv MapPartitionsRDD[1] at textFile at <console>:24 []
    |   /bigdata/GeoIPCity.utf-8.csv HadoopRDD[0] at textFile at <console>:24 []
```

Save Result
```
counts.saveAsTextFile("/bigdata/res1-text")

20/03/14 23:06:57 WARN BlockManager: Block rdd_4_11 could not be removed as it was not found on disk or in memory
20/03/14 23:06:57 WARN BlockManager: Putting block rdd_4_11 failed
```

Fix (+4g):
```
$ ./spark-shell --master "local[4]" --driver-memory 4G
```
OK

Trying to save as binary
```
scala> counts.saveAsObjectFile("/bigdata/res1-obj")
java.lang.IllegalArgumentException: Unsupported class file major version 55
  at org.apache.xbean.asm6.ClassReader.<init>(ClassReader.java:166)
```

Repartition (create new variable due to immutability)
```
val repartitioned = counts.repartition(2)
```

Persist disk only
```
counts.persist(org.apache.spark.storage.StorageLevel.DISK_ONLY)
```

Unpersist from memory
```
scala> counts.unpersist()
res6: counts.type = ShuffledRDD[4] at reduceByKey at <console>:25
```


### Storage levels:
|Level|
|-----|
|MEMORY_ONLY|
|MEMORY_ONLY_SER|
|MEMORY_AND_DISK|
|MEMORY_AND_DISK_SER|
|DISK_ONLY|

### File Formats

|Format|Structured|Desc|
|------|----------|----|
|Text|no|
|JSON|semi|
|CSV|yes|
|Sequence|yes|Hadoop key-valye|
|Protocol Buffers|yes|
|Object files|yes|High speed|

### Consider Hadoop formats:

|Format|Structured|Splittable|Schema evolution|Desc|
|------|----------|----------|----------------|----|
|AVRO  |          |yes       |yes, was mainly designed for Schema evolution. Fields can renamed, added, deleted while old files can still be read with the new schema|Uses JSON for defining, but binary for store |

### Concepts

https://data-flair.training/blogs/apache-spark-rdd-vs-dataframe-vs-dataset/

|Class    |Data Representation|
|---------|-----|
|RDD|RDD is a distributed collection of data elements spread across many machines in the cluster. RDDs are a set of Java or Scala objects representing data.|
|DataFrame|A DataFrame is a distributed collection of data organized into named columns. It is conceptually equal to a table in a relational database.|
|DataSet|It is an extension of DataFrame API that provides the functionality of – type-safe, object-oriented programming interface of the RDD API and performance benefits of the Catalyst query optimizer and off heap storage mechanism of a DataFrame API.|

|Class    |Schema|Data Sources|
|---------|------|------------|
|RDD|-|Text files|
|DataFrame|+|Avro,CSV,JSON,HDFS,Hivetables|
|DataSet|?|?|






## Diagrams



## Versions (latest now)

Component | Version | Desc  
----------|---------|-------
Scala     | 2.12.8  |
Spark Core| org.apache.spark:spark-core:2.4.3 |
Spark SQL | org.apache.spark:spark-sql:2.4.3 |
Spark Avro| org.apache.spark:spark-avro:2.4.3 |

## Dictionary

# RDD 

- Resillent Distributed Datasets

# DataFrame

- 

# DAG

- Direct Acyclic Graph

# Transformations 
(map, flatMap, filter, distinct)
  - with couple arguments (union, intersect, subtract, cathesian)

# Actions 
reduce, fold, aggregate

# Numeric transforma
- mean
- variance


## Examples

Parse text file
```
val df = spark.read().format("csv").option("delimiter", " ").option("header", "false").load(dir_path)
ds.filter("user='shit'").show();
```


```
val dataset = spark.read.parquet("s3://amazon-reviews-pds/parquet/product_category=Electronics/")

import com.amazon.deequ.analyzers.{Compliance, Correlation, Size, Completeness, Mean, ApproxCountDistinct}

val analysisResult: AnalyzerContext = { AnalysisRunner
  // data to run the analysis on
  .onData(dataset)
  // define analyzers that compute metrics
  .addAnalyzer(Size())
  .addAnalyzer(Completeness("review_id"))
  .addAnalyzer(ApproxCountDistinct("review_id"))
  .addAnalyzer(Mean("star_rating"))
  .addAnalyzer(Compliance("top star_rating", "star_rating >= 4.0"))
  .addAnalyzer(Correlation("total_votes", "star_rating"))
  .addAnalyzer(Correlation("total_votes", "helpful_votes"))
  // compute metrics
  .run()
}

// retrieve successfully computed metrics as a Spark data frame
val metrics = successMetricsAsDataFrame(spark, analysisResult)
```

```
import com.amazon.deequ.{VerificationSuite, VerificationResult}
import com.amazon.deequ.VerificationResult.checkResultsAsDataFrame
import com.amazon.deequ.checks.{Check, CheckLevel}

val verificationResult: VerificationResult = { VerificationSuite()
  // data to run the verification on
  .onData(dataset)
  // define a data quality check
  .addCheck(
    Check(CheckLevel.Error, "Review Check") 
      .hasSize(_ >= 3000000) // at least 3 million rows
      .hasMin("star_rating", _ == 1.0) // min is 1.0
      .hasMax("star_rating", _ == 5.0) // max is 5.0
      .isComplete("review_id") // should never be NULL
      .isUnique("review_id") // should not contain duplicates
      .isComplete("marketplace") // should never be NULL
      // contains only the listed values
      .isContainedIn("marketplace", Array("US", "UK", "DE", "JP", "FR"))
      .isNonNegative("year")) // should not contain negative values
```