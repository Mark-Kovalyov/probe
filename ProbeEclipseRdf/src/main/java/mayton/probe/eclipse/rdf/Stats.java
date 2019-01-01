package mayton.probe.eclipse.rdf;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;

public class Stats {

    private Long amount;

    private long position;

    private long beginTime;

    private Long timeRemain;

    public Stats() {
        beginTime = currentTimeMillis();
        amount = null;
        position = 0;
        timeRemain = null;
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
        if (amount != null) {
            percent = (100L * position) / amount;
        }
        return format("position = %d, amount = %s, percent = %s, elapsed time = %s, time remain = %s ",
                position,
                amount == null ? "?" : amount,
                String.valueOf(percent),
                DurationFormatUtils.formatDuration(currentTimeMillis() - beginTime, "H:mm:ss", true) ,
                timeRemain == null ? "?" :
                        DurationFormatUtils.formatDuration(timeRemain, "H:mm:ss", true)
        );
    }
}
