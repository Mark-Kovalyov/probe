package mayton.kafka.serde.avro.typed;

import mayton.kafka.ProducerCallback;
import mayton.kafka.serde.avro.GeoIpCityAvroEntity;
import mayton.kafka.serde.avro.genericrecord.GeoIpGenericProducer;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class GeoIpTypedProducer {



    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(GeoIpTypedProducer.class);

        logger.info("Start");
        Properties properties = new Properties();


        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "");

        //properties.put("SCHEMA", GeoIpCityAvroEntity.getClassSchema());


        properties.put("key.serializer",   "");
        properties.put("value.serializer", "");

        KafkaProducer<String, GenericRecord> producer = new KafkaProducer<>(properties);

        String topic = "geo.ipv4";

        ProducerCallback producerCallback = new ProducerCallback();


        GenericRecord genericRecord = new GenericData.Record(GeoIpCityAvroEntity.getClassSchema());
        genericRecord.put("key", "1");

        ProducerRecord<String, GenericRecord> record = new ProducerRecord<>(topic, genericRecord);

        producer.send(record, producerCallback);

        logger.info("Finish");
    }

}
