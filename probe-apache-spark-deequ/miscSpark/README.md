# Parallelize

```
scala> val intArray = Array(1,2,3,4,5)
intArray: Array[Int] = Array(1, 2, 3, 4, 5)

scala> val rdd1 = sc.parallelize(intArray)
rdd1: org.apache.spark.rdd.RDD[Int] = ParallelCollectionRDD[6] at parallelize at <console>:26
```

# With 6 partition
```
scala> val rdd2 = sc.parallelize(intArray,6)
rdd2: org.apache.spark.rdd.RDD[Int] = ParallelCollectionRDD[7] at parallelize at <console>:26

scala> rdd1.partitions.size
res4: Int = 4

scala> rdd2.partitions.size
res5: Int = 6
        
```