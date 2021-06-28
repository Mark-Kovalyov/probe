# Apache Kafka

* Site https://kafka.apache.org/
* Bugtracker https://issues.apache.org/jira/projects/KAFKA/issues/

## Videos

* https://www.youtube.com/watch?v=A_yUaPARv8U

## Terminology

* Topic
  * contains Partitions
  * Partitions contains segments
  * last (active) segment - ready to receive data  
* Producer
* Consumer
* Broker

### Topic

```
segment = (base_offset, data, index, timeindex)

000001234.log
000001234.index
000001234.timeindex
```

## Configuration

## Zookeeper

```
tickTime=2000
dataDir=/bigdata/zookeeper
clientPort=2181
```
Replicated zookeeper
```
initLimit=5
syncLimit=2
server.1=zoo1:2888:3888
server.2=zoo2:2888:3888
server.3=zoo3:2888:3888
```

## Kafka

```
broker.id = 0 
port=9092
zookeeper.connect=hostname:port/path
log.dirs=/bigdata/kafka
auto.create.topics.enable=true
```

## Producer

```
batch.size = 128K ?
```

## Consumer

## Fine-grained performance tunning

### GC

### Linux-kernel parameters

### FAQ

Q: How to limit wait time when broker is down?