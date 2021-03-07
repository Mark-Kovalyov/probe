#!/bin/bash -v


docker run -it --net=ignite-network \
 -e "CONFIG_URI=$CONFIG_URI" \
 -e "OPTION_LIBS=$OPTION_LIBS" \
 -e "JVM_OPTS=$JVM_OPTS" \
   apacheignite/ignite:2.7.6

# 10800 (TCP JDBC/ODBC), 
# 11211 (TCP connector), 
# 47100 (listener), 
# 47500 (discovery)

docker run -it --net=ignite-network \
 -p 10800:10800 \
 -p 11211:11211 \
 -p 47100:47100 \
 -p 47500:47500 \
 -e "CONFIG_URI=https://raw.githubusercontent.com/apache/ignite/master/examples/config/example-cache.xml" \
    apacheignite/ignite:2.7.6

# CONFIG_URI
#
#    URL to the Ignite
#    configuration file (can
#    also be relative to the
#    META-INF folder on
#    the class path). The
#    downloaded config file
#    will be saved to
#    ./ignite-config.xml

# JVM_OPTS
#
#    Environment variables
#    passed to Ignite
#    instance in your docker

# OPTION_LIBS
#
#    Ignite optional libs
#    which will be included
#    in the class path.

#    default :ignite-log4j,ignite-spring,ignite-indexing

# EXTERNAL_LIBS 
#
#    List of URL’s to libs.