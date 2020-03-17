df = spark.read.format("csv").option("header", "true").load("csvfile.csv")

//In scala,(this works for any format-in delimiter mention "," for csv, "\t" for tsv etc)

val df = sqlContext.read.format("com.databricks.spark.csv")
  .option("delimiter", ",")
  .load("csvfile.csv")


val spark = org.apache.spark.sql.SparkSession.builder
  .master("local")
  .appName("Spark CSV Reader")
  .getOrCreate;

//Use any one of the following ways to load CSV as DataFrame/DataSet

//1. Do it in a programmatic way

val df = spark.read
  .format("csv")
  .option("header", "true") //first line in file has headers
  .option("mode", "DROPMALFORMED")
  .load("hdfs:///csv/file/dir/file.csv")


val geoCity = spark.read.format("csv").option("header", "true").option("delimiter", ",").load("file:///bigdata/GeoIpCity-utf8.cvs")

