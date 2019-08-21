package mayton.probe.eclipse.rdf;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.Immutable;

import java.time.Duration;
import java.time.Instant;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;

public class Sofar {

    @Immutable
    final class SofarSnapshot {

        private final int all;
        private final int sofar;
        private final Instant start;
        private final Instant lastUpdate;
        private final String units;

        public SofarSnapshot(int all, int sofar, Instant start, Instant lastUpdate, String units) {
            this.all = all;
            this.sofar = sofar;
            this.start = start;
            this.lastUpdate = lastUpdate;
            this.units = units;
        }

        public String getTimeElapsed() {
            return DurationFormatUtils.formatDuration(Duration.between(start, lastUpdate).toMillis(), "H:mm:ss", true);
        }

        public String estimateTimeRemain() {
            // TODO: Fix
            long delta = lastUpdate.getEpochSecond() - start.getEpochSecond(); // s
            if (sofar == 0) {
                return "?:??:??";
            } else {
                long x = all * delta / sofar;
                return DurationFormatUtils.formatDuration(Duration.between(start, Instant.ofEpochSecond(x)).toMillis(), "H:mm:ss", true);
            }
        }

        @Override
        public String toString() {
            return format("Processed %d of %d %s(s). Time elapsed: %s. Time remain: %s",
                    sofar, all, units, getTimeElapsed(), estimateTimeRemain()
            );
        }
    }

    private String units;

    private int sofar;

    private Instant start;

    private int all;

    public Sofar(@NotNull String units, int all) {
        this.units = units;
        checkArgument(all >= 0, "Argument was %s but expected nonnegative", sofar);
        this.all = all;
        this.start = Instant.now();
    }

    public void update(int newSofar) {
        checkArgument(newSofar >= sofar, "Argument was %s but expected greather than previous sofar", newSofar);
        sofar = newSofar;
    }

    public SofarSnapshot getSnapshot() {
        return new SofarSnapshot(all, sofar, start, Instant.now(), units);
    }

}
