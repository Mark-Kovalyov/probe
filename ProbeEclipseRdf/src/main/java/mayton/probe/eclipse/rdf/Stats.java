package mayton.probe.eclipse.rdf;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;

public class Stats {

    private long amount;

    private long position;

    private long beginTime;

    public Stats() {
        beginTime = currentTimeMillis();
        amount = -1;
        position = 0;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public String formatStats() {
        long percent = 0;
        if (position != -1 && amount != 0) {
            percent = (100L * position) / amount;
        }
        return format("position = %d, amount = %d, percent = %s, elapsed time = %d s",
                position,
                amount,
                position==-1 ? "?" : String.valueOf(percent),
                (currentTimeMillis() - beginTime) / 1000);
    }
}
