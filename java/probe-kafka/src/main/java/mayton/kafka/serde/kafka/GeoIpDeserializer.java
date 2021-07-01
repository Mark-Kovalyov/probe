package mayton.kafka.serde.kafka;

import mayton.kafka.entities.GeoIpEntity;
import org.apache.kafka.common.serialization.Deserializer;

public class GeoIpDeserializer implements Deserializer<GeoIpEntity> {
    @Override
    public GeoIpEntity deserialize(String topic, byte[] data) {
        return new GeoIpEntity();
    }
}
