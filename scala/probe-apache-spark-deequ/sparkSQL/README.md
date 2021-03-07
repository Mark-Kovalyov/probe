# Spark SQL, DataFrames and Datasets

## Data Frame

## File Formats

### Specifying storage format for Hive tables

A fileFormat is kind of a package of storage format 
specifications, including "serde", "input format" 
and "output format". Currently we support 6 fileFormats: 
'sequencefile', 'rcfile', 'orc', 'parquet', 'textfile' and 'avro

```
CREATE TABLE src(id int) USING hive OPTIONS(fileFormat 'parquet')
```

```
scala> import java.io.File
import java.io.File

scala> import org.apache.spark.sql.{Row, SaveMode, SparkSession}
import org.apache.spark.sql.{Row, SaveMode, SparkSession}

scala> case class Record(key: Int, value: String)
defined class Record

scala> val warehouseLocation = new File("/bigdata/01").getAbsolutePath
warehouseLocation: String = /bigdata/01

scala> val spark = SparkSession.builder().appName("Spark Hive Example").config("spark.sql.warehouse.dir", warehouseLocation).enableHiveSupport().getOrCreate()
20/03/15 16:39:51 WARN SparkSession$Builder: Using an existing SparkSession; some configuration may not take effect.
spark: org.apache.spark.sql.SparkSession = org.apache.spark.sql.SparkSession@1e11d73c

scala> import spark.implicits._
import spark.implicits._

scala> import spark.sql
import spark.sql

scala> sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING) USING hive")
20/03/15 16:41:06 WARN ObjectStore: Version information not found in metastore. hive.metastore.schema.verification is not enabled so recording the schema version 1.2.0
20/03/15 16:41:06 WARN ObjectStore: Failed to get database default, returning NoSuchObjectException
20/03/15 16:41:07 WARN HiveMetaStore: Location: file:/spark/3.0.0-preview2/bin/spark-warehouse/src specified for non-external table:src
res0: org.apache.spark.sql.DataFrame = []

scala> sql("LOAD DATA LOCAL INPATH '/storage/git.spark/spark/sql/hive/src/test/resources/data/files/kv1.txt' INTO TABLE src")
20/03/15 16:43:19 WARN ObjectStore: Failed to get database global_temp, returning NoSuchObjectException
res1: org.apache.spark.sql.DataFrame = []

scala> sql("SELECT COUNT(*) FROM src").show()
+--------+
|count(1)|
+--------+
|     500|
+--------+

scala> val recordsDF = spark.createDataFrame((1 to 100).map(i => Record(i, s"val_$i")))
recordsDF: org.apache.spark.sql.DataFrame = [key: int, value: string]

scala> recordsDF.createOrReplaceTempView("records")

scala> sql("SELECT * FROM records r JOIN src s ON r.key = s.key").show()
+---+------+---+------+
|key| value|key| value|
+---+------+---+------+
| 86|val_86| 86|val_86|
| 27|val_27| 27|val_27|
| 98|val_98| 98|val_98|
| 66|val_66| 66|val_66|
| 37|val_37| 37|val_37|
| 15|val_15| 15|val_15|
| 82|val_82| 82|val_82|
| 17|val_17| 17|val_17|
| 57|val_57| 57|val_57|
| 20|val_20| 20|val_20|
| 92|val_92| 92|val_92|
| 47|val_47| 47|val_47|
| 72|val_72| 72|val_72|
|  4| val_4|  4| val_4|
| 35|val_35| 35|val_35|
| 54|val_54| 54|val_54|
| 51|val_51| 51|val_51|
| 65|val_65| 65|val_65|
| 83|val_83| 83|val_83|
| 12|val_12| 12|val_12|
+---+------+---+------+
only showing top 20 rows


scala> sql("CREATE TABLE hive_records(key int, value string) STORED AS PARQUET")
20/03/15 16:53:29 WARN HiveMetaStore: Location: file:/spark/3.0.0-preview2/bin/spark-warehouse/hive_records specified for non-external table:hive_records
res5: org.apache.spark.sql.DataFrame = []

scala> val df = spark.table("src")
df: org.apache.spark.sql.DataFrame = [key: int, value: string]

scala> 

scala> val dataDir = "/bigdata/tmp/parquet_data"
dataDir: String = /bigdata/tmp/parquet_data

scala> spark.range(10).write.parquet(dataDir)

scala> spark.range(10).write.json("/bigdata/tmp/json_data")

scala> spark.range(10).write.orc("/bigdata/tmp/orc_data")

scala> spark.range(100).write.avro("/bigdata/tmp/avro_data")
<console>:32: error: value avro is not a member of org.apache.spark.sql.DataFrameWriter[Long]
       spark.range(100).write.avro("/bigdata/tmp/avro_data")
                            ^

scala> spark.range(100).write.csv("/bigdata/tmp/csv_data")
```

## Directory /bigdata/tmp

```
.
├── csv_data
│   ├── part-00000-a85b83a2-760c-4930-8124-41664385023a-c000.csv
│   ├── part-00001-a85b83a2-760c-4930-8124-41664385023a-c000.csv
│   ├── part-00002-a85b83a2-760c-4930-8124-41664385023a-c000.csv
│   ├── part-00003-a85b83a2-760c-4930-8124-41664385023a-c000.csv
│   └── _SUCCESS
├── json_data
│   ├── part-00000-8554569d-3df6-46bd-b1c0-dc07826fd5cf-c000.json
│   ├── part-00001-8554569d-3df6-46bd-b1c0-dc07826fd5cf-c000.json
│   ├── part-00002-8554569d-3df6-46bd-b1c0-dc07826fd5cf-c000.json
│   ├── part-00003-8554569d-3df6-46bd-b1c0-dc07826fd5cf-c000.json
│   └── _SUCCESS
├── orc_data
│   ├── part-00000-4a9cae9f-05bb-4c89-8a14-8a25aff582b8-c000.snappy.orc
│   ├── part-00001-4a9cae9f-05bb-4c89-8a14-8a25aff582b8-c000.snappy.orc
│   ├── part-00002-4a9cae9f-05bb-4c89-8a14-8a25aff582b8-c000.snappy.orc
│   ├── part-00003-4a9cae9f-05bb-4c89-8a14-8a25aff582b8-c000.snappy.orc
│   └── _SUCCESS
└── parquet_data
    ├── part-00000-e3cc261b-6b33-43fa-8d4b-00bd09b5da82-c000.snappy.parquet
    ├── part-00001-e3cc261b-6b33-43fa-8d4b-00bd09b5da82-c000.snappy.parquet
    ├── part-00002-e3cc261b-6b33-43fa-8d4b-00bd09b5da82-c000.snappy.parquet
    ├── part-00003-e3cc261b-6b33-43fa-8d4b-00bd09b5da82-c000.snappy.parquet
    └── _SUCCESS
```

