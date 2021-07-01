package mayton.kafka.serde.kafka;

import mayton.kafka.entities.GeoIpEntity;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class GeoIpSerializer implements Serializer<GeoIpEntity> {

    public int estimateForString(String s) {
        return s.getBytes(StandardCharsets.UTF_8).length;
    }

    @Override
    public byte[] serialize(String topic, GeoIpEntity data) {
        int requieredBytes = 4 + 4;
        ByteBuffer byteBuffer = ByteBuffer.allocate(requieredBytes);
        byteBuffer.putInt(data.getStartIpNum());
        byteBuffer.putInt(data.getEndIpNum());

        byteBuffer.put(data.getCountry().getBytes(StandardCharsets.UTF_8));
        return byteBuffer.array();
    }
}
