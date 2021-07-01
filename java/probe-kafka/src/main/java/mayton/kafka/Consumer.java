package mayton.kafka;

import org.apache.commons.cli.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Pattern;

public class Consumer {

    public static Logger logger = LoggerFactory.getLogger(Consumer.class);

    public static Options createOptions() {
        return new Options()
                .addRequiredOption("b", "bootstrap-servers", true, "bootstrap servers (Comma separated)")
                .addRequiredOption("p", "poll-time", true, "Poll timeout (ms)")
                .addRequiredOption("t", "topic-pattern", true, "topic pattern (regex)")
                .addRequiredOption("c", "consumer-id", true, "consumer id")
                .addOption("g", "group-id", true, "group.id");
    }

    public static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar consumer.jar", createOptions(), true);
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

    private static void process(CommandLine line) {
        logger.info("Start");
        Properties properties = new Properties();
        properties.put("bootstrap.servers", line.getOptionValue("bootstrap-servers"));
        properties.put("key.deserializer",   "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", StringDeserializer.class); //"org.apache.kafka.common.serialization.StringDeserializer");
        // TODO: The configuration 'consumer.id' was supplied but isn't a known config
        properties.put("consumer.id", line.getOptionValue("consumer-id"));
        properties.put("group.id", line.getOptionValue("group-id", "0"));

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        int pollTimeMs = Integer.parseInt(line.getOptionValue("poll-time"));

        consumer.subscribe(Pattern.compile(line.getOptionValue("topic-pattern")));

        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(pollTimeMs));
            for(ConsumerRecord<String, String> record : records) {
                logger.info("Received message topic = {}, partition = {}, offset = {}",
                        record.topic(),
                        record.partition(),
                        record.offset());

                if (record.value().equals("poison-pillow")) {
                    break;
                }
            }
        }
    }


}
