set M2HOME=%USERPROFILE%/.m2

set AVRO_VERSION=1.9.2

set JAVA_HOME=c:/JDK-11.0.3
set CLASSPATH=.;%JAVA_HOME%/lib;%M2HOME%/repository/org/slf4j/slf4j-simple/1.7.25/

call mvn dependency:go-offline

java -jar %M2HOME%/repository/org/apache/avro/avro-tools/%AVRO_VERSION%/avro-tools-%AVRO_VERSION%.jar compile schema schema/emp.avsc .
java -jar %M2HOME%/repository/org/apache/avro/avro-tools/%AVRO_VERSION%/avro-tools-%AVRO_VERSION%.jar compile schema schema/geoIpCitySchema.avsc .
