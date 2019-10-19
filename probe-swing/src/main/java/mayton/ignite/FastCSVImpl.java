package mayton.ignite;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.StampedLock;

public class FastCSVImpl implements FastCSV {

    private static final int BUF_SIZE = 2 * 1024 * 1024;

    char[][] buf = new char[BUF_SIZE][2];
    StampedLock[] stampedLock = new StampedLock[2];
    AtomicInteger activeLock;

    public FastCSVImpl(@NotNull InputStream inputStream) {
        stampedLock[0] = new StampedLock();
        stampedLock[1] = new StampedLock();

        long res = stampedLock[0].tryOptimisticRead();
        stampedLock[0].tryReadLock();


        activeLock.getAndIncrement();
        activeLock.getAndDecrement();
    }

    @Override
    public void skip(int lines) {

    }

    @Override
    public int getColumnsSize() {
        return 0;
    }

    @Override
    public boolean nextLine() {
        return false;
    }

    @Override
    public String readString(int column) {
        return null;
    }

    @Override
    public double readDouble(int column) {
        return 0;
    }

    @Override
    public int readInt(int column) {
        return 0;
    }

    @Override
    public long readLong(int column) {
        return 0;
    }
}
