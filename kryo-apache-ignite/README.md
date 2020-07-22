Configure
=========

JAVA_HOME=/jdk/8 && CLASSPATH=$JAVA_HOME/lib && PATH=$JAVA_HOME/bin:$PATH


Watch ports
===========

watch -n 5 "netstat -plnt | grep -E '(10800|11211|47100|47500)'"

10800 (JDBC/ODBC),
11211 (TCP connector),
47100 (listener),
47500 (discovery)


Kill down fucken memcached that takes port 11211
==================================================

$ service memcached stop


When "Ignite cluster is unavailable"
====================================

$ ./control.sh --activate

Control utility [ver. 2.7.0#20181130-sha1:256ae401]
2018 Copyright(C) Apache Software Foundation
--------------------------------------------------------------------------------
Cluster activated


Connect with SQLLine
====================

./sqlline.sh --verbose=true --color=true -u "jdbc:ignite:thin://127.0.0.1:10800"


Usefull links
=============

https://ignite.apache.org/releases/latest/javadoc/index.html


https://www.youtube.com/watch?v=jyOHG7ynWnw

Apache Ignite Persistence / Артем Шитов (GridGain)
https://www.youtube.com/watch?v=sd1E3DKRbzE

Alexey Goncharuk
Sergey Puchnin


Visor
=====

Some useful commands:
+--------------------------------------------+
| Type 'top'    | to see full topology.      |
| Type 'node'   | to see node statistics.    |
| Type 'cache'  | to see cache statistics.   |
| Type 'tasks'  | to see tasks statistics.   |
| Type 'config' | to see node configuration. |
+--------------------------------------------+