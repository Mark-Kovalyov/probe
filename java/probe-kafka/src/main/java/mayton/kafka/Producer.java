package mayton.kafka;

import org.apache.commons.cli.*;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static mayton.kafka.Constants.POISON_PILLOW;
import static mayton.kafka.PrimeProducer.isPrime;

public class Producer {

    public static Logger logger = LoggerFactory.getLogger(Producer.class);

    public static Options createOptions() {
        return new Options()
                .addRequiredOption("b", "bootstrap-servers", true, "bootstrap servers (Comma separated)")
                .addRequiredOption("t", "topic", true, "topic");
    }

    public static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar producer.jar", createOptions(), true);
    }

    public static void main(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = createOptions();
        if (args.length == 0) {
            printHelp();
        } else {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("h")) {
                printHelp();
                return;
            }
            process(line);
        }
    }

    private static class ProducerCallback implements Callback {

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            if (exception != null) {
                logger.error("!", exception);
            } else {
                logger.info("Successfully sent with offset = {}", metadata.offset());
            }
        }
    }

    private static void process(CommandLine line) {
        logger.info("Start");
        Properties properties = new Properties();
        properties.put("bootstrap.servers", line.getOptionValue("bootstrap-servers"));
        properties.put("key.serializer",   "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        String topic = line.getOptionValue("topic");

        ProducerCallback producerCallback = new ProducerCallback();

        for (int i = 0; i < 1_000_0000; i++) {
            if (isPrime(i)) {
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, String.valueOf(i));
                producer.send(record, producerCallback);
            }
        }

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, POISON_PILLOW);
        producer.send(record, producerCallback);

        logger.info("Finish");
    }


}
