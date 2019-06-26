package mayton.probe.ignite.entities;

import java.io.*;

public class BiTemporal implements Externalizable, Serializable {

    private long[]   beginTs;
    private long[]   endTs;
    private long[]   keys;
    private double[] values;

    private int size;

    public BiTemporal() {

    }

    public BiTemporal(int size) {
        this.size = size;
        beginTs = new long[size];
        endTs = new long[size];
        keys = new long[size];
        values = new double[size];
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


    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    // TODO: Optimize performance
    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(size);
        byte[] arr = new byte[size * 4];
        for(int i = 0; i < size; i++) {
            long v = beginTs[i];
            for(int k = 0; k < 8 ; k++) {
                arr[i] = (byte) (v & 0xFF);
                v >>= 8;
            }
        }
        objectOutput.write(arr);
        for(int i = 0; i < size; i++) {
            long v = endTs[i];
            for(int k = 0; k < 8 ; k++) {
                arr[i] = (byte) (v & 0xFF);
                v >>= 8;
            }
        }
        objectOutput.write(arr);
        for(int i = 0; i < size; i++) {
            long v = keys[i];
            for(int k = 0; k < 8 ; k++) {
                arr[i] = (byte) (v & 0xFF);
                v >>= 8;
            }
        }
        objectOutput.write(arr);
        for(int i = 0; i < size; i++) {
            long v = Double.doubleToLongBits(values[i]);
            for(int k = 0; k < 8 ; k++) {
                arr[i] = (byte) (v & 0xFF);
                v >>= 8;
            }
        }
        objectOutput.write(arr);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        size = objectInput.readInt();
        beginTs = new long[size];
        endTs   = new long[size];
        keys    = new long[size];
        values  = new double[size];
        // TODO:
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long[] getKeys() {
        return keys;
    }

    public void setKeys(long[] keys) {
        this.keys = keys;
    }
}
