# Configure
```
export JAVA_HOME=/jdk/8  
export CLASSPATH=$JAVA_HOME/lib  
export PATH=$JAVA_HOME/bin:$PATH
export IGNITE_HOME=/ignite/2.8.0
```

## Watch ports
```
watch -n 5 "netstat -plnt | grep -E '(10800|11211|47100|47500)'"
```
|port|Desc|
|----|----|
|10800| (JDBC/ODBC)
|11211| (TCP connector)
|47100| (listener)
|47500| (discovery)


## Kill down memcached that takes port 11211
```
$ service memcached stop
```

## When "Ignite cluster is unavailable"

```
$ ./control.sh --activate
```
since Ignite 1.10.0
```
$ ./control.sh --set-state ACTIVE
```

## Connect with SQLLine

```
./sqlline.sh --verbose=true --color=true -u "jdbc:ignite:thin://127.0.0.1:10800"
```

## Configure persistence with WAL
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dht-observer-config.cfg" class="org.apache.ignite.configuration.IgniteConfiguration">

        <!-- every 15 seconds -->
        <property name="metricsLogFrequency" value="#{15 * 1000}"/>

        <property name="dataStorageConfiguration">
            <bean class="org.apache.ignite.configuration.DataStorageConfiguration">

                <property name="defaultDataRegionConfiguration">
                    <bean class="org.apache.ignite.configuration.DataRegionConfiguration">
                        <property name="persistenceEnabled" value="true"/>
                    </bean>
                </property>

                <!-- DFLT_PAGE_SIZE = 4096 bytes -->
                <property name="pageSize"       value="4096" />

                <!-- Changing WAL Mode ::= { LOG_ONLY | FSYNC | BACKGROUND | NONE }  -->
                <property name="walMode"        value="BACKGROUND"/>
                <property name="walPath"        value="/bigdata/ignite/2.10.0/wal"/>
                <property name="walArchivePath" value="/bigdata/ignite/2.10.0/wal-archive"/>
                <property name="storagePath"    value="/bigdata/ignite/2.10.0/db"/>

            </bean>
        </property>

    </bean>

</beans>
```

## Usefull links

* https://ignite.apache.org/releases/latest/javadoc/index.html
* https://www.youtube.com/watch?v=jyOHG7ynWnw
* Apache Ignite Persistence / Артем Шитов (GridGain)
* https://www.youtube.com/watch?v=sd1E3DKRbzE

Alexey Goncharuk
Sergey Puchnin

# Visor

Some useful commands:

|Cmd            | Desc                       |
|---------------|----------------------------|
| Type 'top'    | to see full topology.      |
| Type 'node'   | to see node statistics.    |
| Type 'cache'  | to see cache statistics.   |
| Type 'tasks'  | to see tasks statistics.   |
| Type 'config' | to see node configuration. |

