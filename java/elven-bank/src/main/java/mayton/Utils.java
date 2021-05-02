package mayton;

import java.util.Random;

public class Utils {

    private static Random random = new Random();

    private Utils() {

    }

    public static void randomSleepSec(int min, int max) {
        sleepSec(min + random.nextInt(max - min));
    }

    public static void sleepSec(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
