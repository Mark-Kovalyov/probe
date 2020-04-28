package mayton.network.dht;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.StampedLock;

@ThreadSafe
public class LockedStats {

    private StampedLock stampedLock = new StampedLock();
    private int packetsReceived;
    private int packetsRejected;

    public LockedStats(int packetsReceived, int packetsRejected) {
        this.packetsReceived = packetsReceived;
        this.packetsRejected = packetsRejected;
    }

    public LockedStats() {
    }

    public void incPacketsReceived() {

    }

    public Stats sumStats(Stats that) {
        long stamp = stampedLock.readLock();
        Stats res = null;
        try {
            long ws = stampedLock.tryConvertToWriteLock(stamp);
            if (ws != 0L) {
                res = new Stats(packetsReceived + that.packetsReceived, packetsRejected + that.packetsRejected);
            }
        } finally {
            stampedLock.unlock(stamp);
        }
        return res;
    }

    @Override
    public String toString() {
        return "packetsReceived = " + packetsReceived + ", packetsRejected = " + packetsRejected;
    }

    public int getPacketsReceived() {
        long stamp = stampedLock.tryOptimisticRead();
        int res = 0;
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                res = this.packetsReceived;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return res;
    }


    public int getPacketsRejected() {
        long stamp = stampedLock.tryOptimisticRead();
        int res = 0;
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                res = this.packetsReceived;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return res;
    }
}
