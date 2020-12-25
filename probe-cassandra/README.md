# Apache Cassandra

## Network ports

|Port |Service|
|-----|-------|
| 7199|       |
| 7000|       |
| 9042|       |
|36837|       |

## Prereq

Dowgrade console to Java 8
```
#!/bin/bash 

JAVA_HOME=/jdk/8
CLASSPATH=$JAVA_HOME/lib
PATH=$JAVA_HOME/bin:$PATH

export JAVA_HOME
export CLASSPATH
export PATH

PS1="java8> "

export PS1

bash
```
Install Python 2.7 (cqlsh required)
```
$ sudo apt install python2
```
Run shell
```
$ ./cqlsh
Connected to Test Cluster at 127.0.0.1:9042.
[cqlsh 5.0.1 | Cassandra 3.11.9 | CQL spec 3.4.4 | Native protocol v4]
Use HELP for help.
cqlsh> 
cqlsh>

```

## Describe all

```
cqlsh> describe keyspaces

system_traces  system_schema  system_auth  system  system_distributed

cqlsh> describe cluster

Cluster: Test Cluster
Partitioner: Murmur3Partitioner

```

## Cassandra implementation technical details

compaction 
* org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy

compression 
* org.apache.cassandra.io.compress.LZ4Compressor

partitioners
* Murmur3Partitioner
* RandomPartitioner
* OrderPreservingPartitioner
* ByteOrderPartitioner

snitch
* SimpleSnitch
* PropertyFileSnitch
* GossipPropertyFileSnitch
* RackInferringSnitch
* Ec2Snitch
* GoogleCloudSnitch
* CloudStackSnitch
* DynamicEndpointSnitch

## Videos 

* https://www.youtube.com/watch?v=Ae4GABykRoM