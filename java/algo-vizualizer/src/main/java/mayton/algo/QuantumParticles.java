package mayton.algo;

import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;

public class QuantumParticles {

    int[] counters = new int[5];

    public int nextQuantumFiveToThreeState(int x) {
        checkArgument(x >= 0 && x <= 5);
        // |    0    |    1    |    2    |.....
        // |  0  |  1  |  2  |  3  |  4  |.....
        int res = -1;
        switch (x) {
            case 0 :
                res = 0;
                break;
            case 1 :
                res = counters[x] % 3 < 2 ? 0 : 1;
                break;
            case 2 :
                res = 1;
                break;
            case 3 :
                res = counters[x] % 3 < 1 ? 1 : 2;
                break;
            default:
                res = 2;
                break;
        }
        counters[x]++;
        return res;
    }

    public static void main(String[] args) {

        QuantumParticles quantumParticles = new QuantumParticles();

        int feedbackHistogram[] = new int[3];
        IntStream.range(0, 3000).forEach(i -> {
            int factor = i % 5;
            int feedback = quantumParticles.nextQuantumFiveToThreeState(factor);
            feedbackHistogram[feedback]++;
            System.out.printf("input = %d, output = %d\n", factor, feedback);
        });

        for(int i = 0 ; i < feedbackHistogram.length;i++) {
            System.out.printf(" %d : %d\n", i, feedbackHistogram[i]);
        }
    }

}
