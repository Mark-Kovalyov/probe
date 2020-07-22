package mayton.probe.ignite.entities;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class BiTemporalValueKryoSerializer extends Serializer<BiTemporalValue> {

    @Override
    public void write(Kryo kryo, Output output, BiTemporalValue object) {
        output.writeInt(object.getSize());
        output.writeLongs(object.getBeginTs());
        output.writeLongs(object.getEndTs());
        output.writeLongs(object.getKeys());
        output.writeDoubles(object.getValues());
    }

    @Override
    public BiTemporalValue read(Kryo kryo, Input input, Class<BiTemporalValue> type) {
        BiTemporalValue biTemporalValue = new BiTemporalValue();
        int size = input.readInt();
        biTemporalValue.setSize(size);
        biTemporalValue.setBeginTs(input.readLongs(size));
        biTemporalValue.setEndTs(input.readLongs(size));
        biTemporalValue.setKeys(input.readLongs(size));
        biTemporalValue.setValues(input.readDoubles(size));
        return biTemporalValue;
    }

}
