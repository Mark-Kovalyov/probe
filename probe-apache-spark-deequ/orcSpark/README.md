# ORC + Spark

```
$ ./spark-shell --master "local[4]" --driver-memory 4G \
   -Dspark.sql.orc.impl=native \
   -Dspark.sql.orc.enabled=true \
   -Dspark.sql.orc.enableVectorizedReader=true
```

```
import org.apache.spark.sql._
val sqlContext = new org.apache.spark.sql.hive.HiveContext(sc)

val records = (1 to 100).map { i =>
    Person(s"name_$i", i, (0 to 1).map { m => Contact(s"contact_$m", s"phone_$m") })
}

sc.parallelize(records).toDF().write.format("orc").save("people")
```

```
val people = sqlContext.read.format("orc").load("people")
```


## Hive Context Deprecated?

```
scala> val sqlContext = new org.apache.spark.sql.hive.HiveContext(sc)
<console>:24: warning: class HiveContext in package hive is deprecated: Use SparkSession.builder.enableHiveSupport instead
       val sqlContext = new org.apache.spark.sql.hive.HiveContext(sc)
                                                      ^
sqlContext: org.apache.spark.sql.hive.HiveContext = org.apache.spark.sql.hive.HiveContext@7f65639e
```

```
// Create a SparkSession. No need to create SparkContext
// You automatically get it as part of the SparkSession
val warehouseLocation = "file:${system:user.dir}/spark-warehouse"
val spark = SparkSession
   .builder()
   .appName("SparkSessionZipsExample")
   .config("spark.sql.warehouse.dir", warehouseLocation)
   .enableHiveSupport()
   .getOrCreate()

```