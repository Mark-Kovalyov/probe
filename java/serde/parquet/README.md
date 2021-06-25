# Apache Parquet

## Extension



## Features

* Column-oriented file format
* Row group: A group of rows in columnar format
  * Max size buffered in memory while writing
  * One or more per split while reading
  * 50 Mb < row group < 1GB
    
* Column chunk: The data for one column in a row group
  * CC can be read independently for efficien scans
    
* Page: Unit of access in a column chunk
  * Should be big enough for compression to be efficient
  * Minimum size to read access a single record  
  * 8KB < page < 1MB  

## Parquet tools

http://www.mtitek.com/tutorials/bigdata/hadoop/parquet-tools.php

### Cat

Prints out content for a given parquet file.
```
java -jar parquet-tools-1.11.1.jar cat --help
parquet-cat:
Prints the content of a Parquet file. The output contains only the data, no
metadata is displayed
usage: parquet-cat [option...] <input>
where option is one of:
       --debug     Enable debug output
    -h,--help      Show this help string
    -j,--json      Show records in JSON format.
       --no-color  Disable color output even if supported
where <input> is the parquet file to print to stdout
```

### Head

Prints out the first n records for a given parquet file (default: 5).

### Schema

Prints out the schema for a given parquet file.

### Meta

Prints out metadata for a given parquet file.

### Dump

  Prints out row groups and metadata for a given parquet file.

```
java -jar parquet-tools-1.11.1.jar dump --help
parquet-dump:
Prints the content and metadata of a Parquet file
usage: parquet-dump [option...] <input>
where option is one of:
    -c,--column <arg>  Dump only the given column, can be specified more than
                       once
    -d,--disable-data  Do not dump column data
       --debug         Enable debug output
    -h,--help          Show this help string
    -m,--disable-meta  Do not dump row group and page metadata
    -n,--disable-crop  Do not crop the output based on console width
       --no-color      Disable color output even if supported
where <input> is the parquet file to print to stdout

```

### Merge

  Merges multiple Parquet files into one Parquet file.

## File format

```
"PAR1 
 Row group 0
   Column a
     Page 0
      - Page header (ThriftCompact Protocol)
      - Repetition levels
      - Definition levels
      - values
     Page 1....
   Column b ....  
 Row group 1
 ....
 Footer
   FileMetaData (ThriftCompact Protocol)
   - Version
   - Scjema
   - extra key/value pairs
     Row Geoup 0 meta data"
     - type/path/encoding/codec
     - num values
     ....
 Footer length
 Magic Number "PAR1"    
 
 
```

## Links

* http://parquet.apache.org/
* https://www.youtube.com/watch?v=Qfp6Uv1UrA0
* https://www.youtube.com/watch?v=1j8SdS7s_NY