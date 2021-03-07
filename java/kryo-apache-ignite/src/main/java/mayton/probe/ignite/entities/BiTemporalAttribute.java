package mayton.probe.ignite.entities;

import com.esotericsoftware.kryo.DefaultSerializer;

@DefaultSerializer(BiTemporalAttributeKryoSerializer.class)
public class BiTemporalAttribute {

    private int size;
    private long[]   beginTs;
    private long[]   endTs;
    private long[]   keys;
    private Object[] objects;

    public BiTemporalAttribute() {

    }

    public BiTemporalAttribute(int size) {
        this.size = size;
        beginTs = new long[size];
        endTs = new long[size];
        keys = new long[size];
        objects = new Object[size];
    }


    public long[] getBeginTs() {
        return beginTs;
    }

    public void setBeginTs(long[] beginTs) {
        this.beginTs = beginTs;
    }

    public long[] getEndTs() {
        return endTs;
    }

    public void setEndTs(long[] endTs) {
        this.endTs = endTs;
    }

    public long[] getKeys() {
        return keys;
    }

    public void setKeys(long[] keys) {
        this.keys = keys;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
