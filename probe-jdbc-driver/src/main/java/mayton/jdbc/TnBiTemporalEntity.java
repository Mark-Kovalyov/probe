package mayton.jdbc;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class TnBiTemporalEntity {

    private final long beginTs;
    private final long endTs;
    private final long key;
    private final double value;

    public TnBiTemporalEntity(long beginTs, long endTs, long key, double value) {
        this.beginTs = beginTs;
        this.endTs = endTs;
        this.key = key;
        this.value = value;
    }

    public long getBeginTs() {
        return beginTs;
    }

    public long getEndTs() {
        return endTs;
    }

    public long getKey() {
        return key;
    }

    public double getValue() {
        return value;
    }
}
