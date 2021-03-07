package mayton.probe.ignite.entities;

public class BiTemporalValue {

    private long[]   beginTs;
    private long[]   endTs;
    private long[]   keys;
    private double[] values;

    private int size;

    public BiTemporalValue() {

    }

    public BiTemporalValue(int size) {
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
