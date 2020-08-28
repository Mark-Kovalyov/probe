FROM openjdk:11-jdk-alpine

ARG JAR_FILE=target/tr-loader-1.0-SNAPSHOT.jar
ARG JAR_LIB_FILE=target/lib/

WORKDIR /usr/local/runme

COPY ${JAR_FILE} tr-loader.jar
ADD ${JAR_LIB_FILE} lib/
ENTRYPOINT ["java","-jar","tr-loader.jar"]