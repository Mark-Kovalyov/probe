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
start
```
???
```
## Kafka

```
broker.id=0
listeners=PLAINTEXT://:9092
num.network.threads=3
num.io.threads=8

socket.send.buffer.bytes=102400
socket.receive.buffer.bytes=102400
socket.request.max.bytes=104857600

log.dirs=/bigdata/kafka/kafka-logs
num.partitions=1
num.recovery.threads.per.data.dir=1
offsets.topic.replication.factor=1
transaction.state.log.replication.factor=1
transaction.state.log.min.isr=1

#log.retention.hours=168
log.retention.minutes=10
log.segment.bytes=1073741824
log.retention.check.interval.ms=300000

zookeeper.connect=localhost:2181
zookeeper.connection.timeout.ms=18000

group.initial.rebalance.delay.ms=0

```
start
```
bin/kafka-server-start.sh config/server.properties
```

## Producer

```
client.id = "Java11.Kafka.application"
acks = { 0 | 1 | all }
batch.size = 128K ?
linger.ms = ?
retries = ?
compression.type = { snappy | gzip | lz4 }
max.block.ms = ?
max.request.size
receive.buffer.bytes = ?
send.buffer.bytes = ?
```

## Configure retention for server
```
log.retention.minutes=10
```

## Consumer

## Performance tunning

### G1GC

```
-server
-XX:UseG1GC
-XX:MaxGCPauseMillis=200
-XX:InitiatingHeapOccupancyPercent=45
-XX:DisableExplicitGC
-Djava.aws.headless=true

```

### Linux-kernel parameters

```
vm.swappiness = 1
vm.dirty_background_ratio = 5
vm.dirty_ratio = 80
```

### Disk

* EXT4
* XFS
* Disable "atime" update

### Network

```
net.core.wmem_default = 2Mb
net.core.rmem_default = 2Mb
net.ipv4.tcp_wmem = 4096 65536 2048000
net.ipv4.tcp_rmem = 4096 65536 2048000
```

### FAQ

Q: How to limit wait time when broker is down?