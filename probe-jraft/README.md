# Clustering and Discovery algorithms (opensource) and other tecnhologies

## Software and algorithms (abbreviations)

|Name|Uses or depends on|
|--------|--------|
|MongoDb |Raft (since 3.2)|
|Apache Ignite|TCP/IP discovery(?), ZooKeeper Discovery|
|Infinispan|2PC ?|
|Apache Cassandra|Paxos ?, Gossip protocol, Phi Accural Failure Detection|
|Hazelcast|Raft ?|
|Apache Storm|Nimbus ?|
|ZooKeeper|ZAB|
|Apache Cudu|Raft ?|
|Consul||
|PGPool||
|Logical Clock||
|two-phase commit protocol (2PC)||
|three-phase commit protocol (3PC)||
|ZAB (ZooKeeper Atomic Broadcast protocol)||
|Patroni||

## Links 

### Raft

* In Search of an Understandable Consensus Algorithm - https://raft.github.io/raft.pdf
* Visualization - https://raft.github.io/
* Designing for Understandability: The Raft Consensus Algorithm - https://www.youtube.com/watch?v=vYp4LYbnnW8

### Paxos  

* Understanding Paxos - https://www.cs.rutgers.edu/~pxk/417/notes/paxos.html
* A New Adaptive Accrual Failure Detector for Dependable Distributed Systems - https://core.ac.uk/download/pdf/188778439.pdf

### Zab

* Zab vs Paxos - https://cwiki.apache.org/confluence/display/ZOOKEEPER/Zab+vs.+Paxos

### PAFD

* Phi φ Accrual Failure Detection - https://medium.com/@often_weird/phi-%CF%86-accrual-failure-detection-79c21ce53a7a

## See also
* ZooKeeper - https://zookeeper.apache.org/
* The Chubby lock service for loosely-coupled distributed systems - https://storage.googleapis.com/pub-tools-public-publication-data/pdf/c64be13661eaea41dcc4fdd569be4858963b0bd3.pdf
* Hazelcast IMDG 3.12 Introduces CP Subsystem - https://hazelcast.com/blog/hazelcast-imdg-3-12-introduces-cp-subsystem/
* Architecture of ZAB – ZooKeeper Atomic Broadcast protocol - https://distributedalgorithm.wordpress.com/2015/06/20/architecture-of-zab-zookeeper-atomic-broadcast-protocol/

