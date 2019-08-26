package mayton.probe.eclipse.rdf;

public class CumulativeTimerNano {

    private long startTimeNano;
    private long cumulativeTimeNano;

    public void start() {
        startTimeNano = System.nanoTime();
    }

    public void end() {
        cumulativeTimeNano += (System.nanoTime() - startTimeNano);
    }

    public void reset() {
        startTimeNano = 0;
        cumulativeTimeNano = 0;
    }

    public long getCumulativeTimeNano() {
        return cumulativeTimeNano;
    }
}
