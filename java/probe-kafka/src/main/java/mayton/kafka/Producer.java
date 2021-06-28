package mayton.kafka;

import org.apache.commons.cli.*;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {

    public static Logger logger = LoggerFactory.getLogger(Producer.class);

    public static Options createOptions() {
        return new Options()
                .addRequiredOption("b", "bootstrap-servers", true, "bootstrap servers (Comma separated)");
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

    private static class PrimeCallback implements Callback {

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            if (exception != null) {
                logger.error("!", exception);
            }
        }
    }

    private static void process(CommandLine line) {
        logger.info("Start");
        Properties properties = new Properties();
        properties.put("bootstrap.servers", line.getOptionValue("bootstrap-servers")); //"localhost:9092");
        properties.put("key.serializer",   "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        String topic = "math.primes";

        for(int i = 0; i < 100_000; i++) {
            if (isPrime(i)) {
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, Integer.toString(i));
                producer.send(record, new PrimeCallback());
            }
        }

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "poison-pillow");
        producer.send(record, new PrimeCallback());

        logger.info("Finish");
    }

    private static boolean isPrime(int i) {
        int max = 1 + (int) Math.sqrt(i);
        if (i % 2 == 0) return false;
        for(int j = 3; j < max; j+=2) {
            if (i % j == 0) return false;
        }
        return true;
    }
}
