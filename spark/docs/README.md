# FAQ:

Q: RDD/DataFrame/Dataset. What is about? How to choose best aproach?

A: See articles:
* https://data-flair.training/blogs/apache-spark-rdd-vs-dataframe-vs-dataset/


Q: How to load custom splitter csv file:

A:
```
import org.apache.spark.sql.DataFrame
val dataFrame1 : DataFrame = spark.read.option("header", true).option("delimiter","|").csv("/home/user/file.csv")
```

Q: How to read from Parquet

A:
```
val dataset = spark.read.parquet("s3://amazon-reviews-pds/parquet/product_category=Electronics/")
```

Q: 
```
scala> dataFrame1.count
21/02/16 13:26:38 WARN package: Truncated the string representation of a plan since it 
was too large. This behavior can be adjusted by setting 'spark.sql.debug.maxToStringFields'.
res0: Long = 3999999
```
A: Lets calculate with Linux utils

```
$ wc -L /home/user/file.csv
2767 /home/user/file.csv

????
```

Q:
```
21/02/16 13:43:46 ERROR ShutdownHookManager: Exception while deleting Spark temp dir: C:\Users\user\AppData\Local\Temp\spark-b1da4db8-4c04-4dce-abfd-1d751809012e\repl-6db8f3d9-0d3e-4475-b8d8-0ea4e7d1f782
java.io.IOException: Failed to delete: ....................
at org.apache.spark.network.util.JavaUtils.deleteRecursivelyUsingJavaIO(JavaUtils.java:144)
```
A:
?

Q: How to show columns of DataFrame? (Without limitation??)
A:
```
dataFrame1.columns
```

Q: How to calculate DataFrame lines?
A:
```
dataFrame1.count
```

Q: How to show 'lazy DataFrame' calculation result

A: 
```
scala> x.head
res9: org.apache.spark.sql.Row = [1000046]

scala> x.show
+----------------+
|min(DUNS NUMBER)|
+----------------+
|         1000046|
+----------------+
```

Q: How to force 10 rows show result, without limitation 

A:
```
xSelection.show(10,false)
```

Q: API? More examples?

A: 
* https://spark.apache.org/docs/latest/api/scala/org/apache/spark/index.html
* https://sparkbyexamples.com/spark

Q: Cache and Persist

A:
```
...dataFrame1.cache()
...dataFrame2.persist(org.apache.spark.storage.StorageLevel.MEMORY_AND_DISK)
```

Q: How to calculate hash

A:
```
import org.apache.spark.sql.functions;
Column stringCol = new Column("stringValues");
trainingDF = trainingDF.withColumn("hashString", functions.hash(stringCol));
```

Q: How to filter by expression from DNA database

A:

```
val y = dnaSelection.filter("`LOB` == 'SPACEX'")
val z = dnaSelection.filter(col("LOB").like("SPACEX%"))
val w = dnaSelection.filter(col("LOB").like("SPACEX%") && col("YS") > 1950)
```

Q: Type casting

A:
```
import org.apache.spark.sql.types._
val z2 = dnaSelection.filter((col("LOB") === "SPACEX") && (col("YS").cast(IntegerType) > 1950))
```