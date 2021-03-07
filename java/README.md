# Java

## XmX

### CompressedOops

Heap with 47G less than 32G

Conf1
```
java -Xms31g -Xmx31g -Mxn50m
```

Conf2
```
java -Xms32g -Xmx32g -Xmn50m
```
See: https://wiki.openjdk.java.net/display/HotStop/CompressedOops
