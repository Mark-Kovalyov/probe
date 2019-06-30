package mayton.probe.ignite.entities;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class BiTemporalAttributeKryoSerializer extends Serializer<BiTemporalAttribute> {


    @Override
    public void write(Kryo kryo, Output output, BiTemporalAttribute object) {
        int size = object.getSize();
        output.writeInt(object.getSize());
        output.writeLongs(object.getBeginTs());
        output.writeLongs(object.getEndTs());
        output.writeLongs(object.getKeys());
        // TODO: Object?
        Object[] objects = object.getObjects();
        for(int i = 0;i<size;i++) {
            if (objects[i] instanceof String) {
                output.writeInt(0);
                output.writeAscii((String) objects[i]);
            }
        }
    }

    @Override
    public BiTemporalAttribute read(Kryo kryo, Input input, Class<BiTemporalAttribute> type) {
        BiTemporalAttribute biTemporalAttribute = new BiTemporalAttribute();
        int size = input.readInt();
        biTemporalAttribute.setBeginTs(input.readLongs(size));
        biTemporalAttribute.setEndTs(input.readLongs(size));
        biTemporalAttribute.setKeys(input.readLongs(size));
        Object[] objects = new Object[size];
        for(int i = 0;i<size;i++) {
            int dataType = input.readInt();
            if (dataType == 0) {
                objects[i] = input.readString();
            }
        }
        return biTemporalAttribute;
    }
}
