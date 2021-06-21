# Hadoop

```
$ echo $HADOOP_HOME
/hadoop/3.1.2
$ java -version
openjdk version "11.0.11" 2021-04-20
OpenJDK Runtime Environment (build 11.0.11+9-Ubuntu-0ubuntu2.20.04)
OpenJDK 64-Bit Server VM (build 11.0.11+9-Ubuntu-0ubuntu2.20.04, mixed mode, sharing)
```

## Getting Started

### Create user hadoop

```
useradd hadoop
passwd hadoop 
****
```
Yet another sample:
```
sudo adduser --ingroup hadoop hadoopusr
```

### SSH
```
ssh-keygen -t rsa
```
Add new public key to authorized
```
cat .ssh/id_rsa.pub >> .ssh/authorized_keys
```
### Configure
core-site.xml
```
+
 <property>
  <name>fs.defaultFS</name>
  <value>hdfs://localhost:9000</value>
 </property>
```
hdfs-site.xml
```
+
 <property>
  <name>dfs.replication</name>
  <value>1</value>
 </property>
 <property>
  <name>dfs.namenode.name.dir</name>
  <value>/home/hadoop/hdfs/namenode/</value>
 </property>
 <property>
  <name>dfs.namenode.data.dir</name>
  <value>/home/hadoop/hdfs/datanode/</value>
 </property>
```
hadoop-env.sh
```
+
JAVA_HOME=.....
```

### Start NameNode

```
$ ./hdfs namenode -format
```
### Start DFS
```
./start-dfs.sh
```
Proced to web UI https://localhost:9870

### Stop all Apache Hadoop daemons

```
./stop-all.sh
```

## Links

* https://www.youtube.com/watch?v=EJj_0o-EY50
* https://www.youtube.com/watch?v=ieeCyhQ2PPM
