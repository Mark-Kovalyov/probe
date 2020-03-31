aws emr create-cluster --name "Probe-Distinct" --release-label emr-5.29.0 --applications Name=Spark \
 --ec2-attributes KeyName=myKey \
 --instance-type m5.xlarge --instance-count 2 \
 --steps Type=Spark,Name="Spark Program",ActionOnFailure=CONTINUE,Args=[--class,org.apache.spark.examples.SparkPi,/usr/lib/spark/examples/jars/spark-examples.jar,10] \
 --use-default-roles

# or

aws emr create-cluster --name "Add Spark Step Cluster" --release-label emr-5.29.0 \
--applications Name=Spark --ec2-attributes KeyName=myKey --instance-type m5.xlarge --instance-count 3 \
--steps Type=CUSTOM_JAR,Name="Spark Program",Jar="command-runner.jar",ActionOnFailure=CONTINUE,Args=[spark-example,SparkPi,10] --use-default-roles

