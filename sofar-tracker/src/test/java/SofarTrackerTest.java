import mayton.lib.SofarTracker;
import org.junit.Test;

public class SofarTrackerTest {

    @Test(expected = IllegalArgumentException.class)
    public void createUnitLikeTrackerWithNegativeArg() {
        SofarTracker sofarTracker = SofarTracker.createUnitLikeTracker("", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFileSizeTrackerWithNegativeArg() {
        SofarTracker sofarTracker = SofarTracker.createFileSizeTracker(-1);
    }

}
