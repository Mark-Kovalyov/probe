# Spark + Deequu

**Keywords:SPARK,DEEQU,ORC,RDD**

## General Links

**Spark** https://spark.apache.org/ 

**ORC** https://orc.apache.org/

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

Retieve RDD from text file
```
$ spark-shell --master "local[4]"

scala> val inputFile = sc.textFile("/tmp/show-spark/input.txt"
```

Count words:
```
val counts = inputFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _);
```

Debug
```
counts.toDebugString
```

Save
```
counts.save(...)
```

Repartition (create new variable due to immutability)
```
val repartitioned = counts.repartition(5)
```

Persist
```
result.persist(StorageLevel.DISK_ONLY)
```

Storage levels:

|Level|
|-----|
|MEMORY_ONLY|
|MEMORY_ONLY_SER|
|MEMORY_AND_DISK|
|MEMORY_AND_DISK_SER|
|DISK_ONLY|


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

Resillent Distributed Datasets

# Transformations 
(map, flatMap, filter, distinct)
  - with couple arguments (union, intersect, subtract, cathesian)

# Actions 
reduce, fold, aggregate

# Numeric transforma
- mean
- variance


## Examples



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