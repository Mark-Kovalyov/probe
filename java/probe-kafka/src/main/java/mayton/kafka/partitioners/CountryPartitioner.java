package mayton.kafka.partitioners;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.InvalidRecordException;
import org.apache.kafka.common.requests.ProduceResponse;

import java.util.Map;

public class CountryPartitioner implements Partitioner {

    @Override
    public void configure(Map<String, ?> configs) {

    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if (key == null || !(key instanceof String)) {
            throw new InvalidRecordException("key is null or not instance of string");
        }
        String country = (String) key;
        if (country.equals("US")) return 0;
        if (country.equals("RU")) return 1;
        return 2 + Math.abs(country.hashCode() % 250);
    }

    @Override
    public void close() {

    }


}
