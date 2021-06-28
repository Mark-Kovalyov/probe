package mayton.kafka;

public class PrimeProducer  {

    public static boolean isPrime(int i) {
        int max = 1 + (int) Math.sqrt(i);
        if (i % 2 == 0) return false;
        for(int j = 3; j < max; j+=2) {
            if (i % j == 0) return false;
        }
        return true;
    }

}
