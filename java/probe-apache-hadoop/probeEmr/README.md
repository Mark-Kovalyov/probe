# EMR

Cluster name:

Region: us-east-1 (Virginia)

Release: emr-5.28.0
App: Spark 2.4.4 on Hadoop 2.8.5 Yarn + Ganglia 3.7.2 + Zeppelin 0.8.2
S3 folder: 
Instance type:  m1.medium
Number: 1 (1 master, 0 core)

Add step:
 +- Custom Jar (arguments, JAR location s3://, AOF)
 +- Streaming program (Name, mapper s3://, reducer, Input s3://, Output s3:// , args, AOF)
 +- Spark (Name, 
          Deploy mode: { Cluster | Client }, 
          Spark-submit options: (text)
          App location: s3://   (Path to a JAR with your application and dependencies 
                        (client deploy mode only supports a local path).
          Arguments:
          AOF
          

## Links

https://docs.aws.amazon.com/emr/latest/ReleaseGuide/emr-spark-launch.html
https://aws.amazon.com/blogs/big-data/test-data-quality-at-scale-with-deequ/
