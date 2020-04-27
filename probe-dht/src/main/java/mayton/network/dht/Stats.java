package mayton.network.dht;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.StampedLock;

@NotThreadSafe
public class Stats {

    public int packetsReceived;
    public int packetsRejected;

    public Stats(int packetsReceived, int packetsRejected) {
        this.packetsReceived = packetsReceived;
        this.packetsRejected = packetsRejected;
    }

    public Stats() {
    }

    public Stats sumStats(Stats that) {
        return new Stats(packetsReceived + that.packetsReceived, packetsRejected + that.packetsRejected);
    }

    @Override
    public String toString() {
        return "packetsReceived = " + packetsReceived + ", packetsRejected = " + packetsRejected;
    }
}
