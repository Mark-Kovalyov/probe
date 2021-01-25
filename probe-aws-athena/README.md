# Athena

- Serverless, no ETL
- Open, powerfull, standard
  - Built on Presto, runs standard SQL
    (CSV, JSON, ORC, Avro, and Parquet)
- Pay per query  
- Fast, really fast

## Create database

```
CREATE DATABASE mtn-db
```

## Create table

```
CREATE EXTERNAL TABLE IF NOT EXISTS mtn-table (
  `Date` DATE,
  Time STRING,
  Location STRING,
  Bytes INT,
  RequestIP STRING,
  ....
  ) ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.RegexSerDe'
  WITH SERDEPROPERTIES (
  "input.regex" = "^(?!#)([^ ]+)\\s+([^ ]+)\\s+([^ ]+)\\s+([^ ]+)\\s+([^ ]+)\\s+([^ ]+)\\s+([^ ]+)\\s+([^ ]+)\\s+([^ ]+)\\s+([^ ]+)\\s+[^\(]+[\(]([^\;]+).*\%20([^\/]+)[\/](.*)$"
  ) LOCATION 's3://athena-examples-myregion/cloudfront/plaintext/';
```

## Compression formats

```
SNAPPY – The default compression format for files in the Parquet data storage format.
ZLIB – The default compression format for files in the ORC data storage format.
LZO
GZIP
BZIP2
```

## Row formats

* org.apache.hadoop.hive.serde2.RegexSerDe
* org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe
* org.apache.hadoop.hive.serde2.avro.AvroSerDe
* com.amazon.emr.hive.serde.CloudTrailSerde
  - INPUTFORMAT 'com.amazon.emr.cloudtrail.CloudTrailInputFormat'
  - OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'
* org.apache.hadoop.hive.serde2.OpenCSVSerde
* com.amazonaws.glue.serde.GrokSerDe
* org.apache.hive.hcatalog.data.JsonSerDe
* org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe
* org.apache.hadoop.hive.ql.io.orc.OrcSerde
* parquet.hive.serde.ParquetHiveSerDe
  - INPUTFORMAT 'parquet.hive.DeprecatedParquetInputFormat'
  - OUTPUTFORMAT 'parquet.hive.DeprecatedParquetOutputFormat'

## SerDe

    Apache Web Logs: "org.apache.hadoop.hive.serde2.RegexSerDe"
    CSV: "org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe"
    TSV: "org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe"
    Custom Delimiters: "org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe"
    Parquet: "org.apache.hadoop.hive.ql.io.parquet.serde.ParquetHiveSerDe"
    Orc: "org.apache.hadoop.hive.ql.io.orc.OrcSerde"
    JSON: “org.apache.hive.hcatalog.data.JsonSerDe” OR org.openx.data.jsonserde.JsonSerDe


## CTAS

```

```

## Bucketing vs Partitioning




