#!/bin/bash -v

# 8161  - Web
# 61616 - OpenWire
# 61613 - Stomp

docker run --name='activemq' -d \
 -e 'ACTIVEMQ_CONFIG_NAME=amqp-srv1' \
 -e 'ACTIVEMQ_CONFIG_DEFAULTACCOUNT=false' \
 -e 'ACTIVEMQ_ADMIN_LOGIN=admin'                 -e 'ACTIVEMQ_ADMIN_PASSWORD=*******************' \
 -e 'ACTIVEMQ_USERS_myproducer=**************'   -e 'ACTIVEMQ_GROUPS_writes=myconsumer' \
 -e 'ACTIVEMQ_USERS_myconsumer=********'         -e 'ACTIVEMQ_GROUPS_reads=myconsumer' \
 -e 'ACTIVEMQ_JMX_user1_role=readwrite'          -e 'ACTIVEMQ_JMX_user1_password=*********' \
 -e 'ACTIVEMQ_JMX_user2_role=read'               -e 'ACTIVEMQ_JMX_user2_password=************' \
 -e 'ACTIVEMQ_CONFIG_TOPICS_topic1=mytopic1'     -e 'ACTIVEMQ_CONFIG_TOPICS_topic2=mytopic2'  \
 -e 'ACTIVEMQ_CONFIG_QUEUES_queue1=myqueue1'     -e 'ACTIVEMQ_CONFIG_QUEUES_queue2=myqueue2'  \
 -e 'ACTIVEMQ_CONFIG_MINMEMORY=1024'             -e 'ACTIVEMQ_CONFIG_MAXMEMORY=4096' \
 -e 'ACTIVEMQ_CONFIG_SCHEDULERENABLED=true' \
 -v /activemq-db/data:/data \
 -v /activemq-db/log:/var/log/activemq \
 -p 8161:8161 \
 -p 61616:61616 \
 -p 61613:61613 \
 webcenter/activemq:5.14.3

# Consider: docker pull rmohr/activemq:5.10.0