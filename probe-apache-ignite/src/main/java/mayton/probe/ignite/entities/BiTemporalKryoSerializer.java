package mayton.probe.ignite.entities;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class BiTemporalKryoSerializer extends Serializer<BiTemporal> {

    @Override
    public void write(Kryo kryo, Output output, BiTemporal object) {
        output.writeInt(object.getSize());
        output.writeLongs(object.getBeginTs());
        output.writeLongs(object.getEndTs());
        output.writeLongs(object.getKeys());
        output.writeDoubles(object.getValues());
    }

    @Override
    public BiTemporal read(Kryo kryo, Input input, Class<BiTemporal> type) {
        BiTemporal biTemporal = new BiTemporal();
        int size = input.readInt();
        biTemporal.setSize(size);
        biTemporal.setBeginTs(input.readLongs(size));
        biTemporal.setEndTs(input.readLongs(size));
        biTemporal.setKeys(input.readLongs(size));
        biTemporal.setValues(input.readDoubles(size));
        return biTemporal;
    }

}
