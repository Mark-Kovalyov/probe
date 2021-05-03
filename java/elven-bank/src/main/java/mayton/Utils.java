package mayton;

import java.util.Random;

public class Utils {

    private static Random random = new Random();

    private Utils() {

    }

    public static long randomSleepSec(int min, int max) {
        return sleepSec(min + random.nextInt(max - min));
    }

    public static long sleepSec(int sec) {
        try {
            Thread.sleep(sec * 1000L);
            return 1000L * sec;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return 0L;
    }

}
