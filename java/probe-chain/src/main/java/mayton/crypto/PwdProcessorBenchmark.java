package mayton.crypto;

import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.Throughput)
public class PwdProcessorBenchmark {

    public static String ALPHABET = "abcdefghjklmnopqrstuvwxyz0123456789";

    public static String HEX_ALPHABET = "0123456789abcdef";

    public static int MAX_PWD_LENGTH = 12;

    public static int SHA1_LENGTH_BITS = 160;

    public static int SHA256_LENGTH_BITS = 256;

    public static int CHAIN_LENGTH = 1000;

    public Random random;

    private String randomStringGen(String alphabet, int maxPwdLength) {
        StringBuilder sb = new StringBuilder(maxPwdLength);
        for (int i = 0; i < maxPwdLength; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    @Setup(value = Level.Iteration)
    public void setup() {
        random = new Random();
    }

    @Benchmark
    @Warmup(iterations = 1, time = 10, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 2, batchSize = 2)
    public void testHash() {
        PwdProcessor.hashPwd(randomStringGen(ALPHABET, MAX_PWD_LENGTH));
    }

    @Benchmark
    @Warmup(iterations = 1, time = 10, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 2, batchSize = 2)
    public void testReverseHash() {
        PwdProcessor.reversePwd(randomStringGen(HEX_ALPHABET, SHA256_LENGTH_BITS / 4), ALPHABET, MAX_PWD_LENGTH);
    }

    @Benchmark
    @Warmup(iterations = 1, time = 10, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 2, batchSize = 2)
    public void testReverseHash2() {
        PwdProcessor.reversePwd2(randomStringGen(HEX_ALPHABET, SHA256_LENGTH_BITS / 4), ALPHABET, MAX_PWD_LENGTH);
    }

}
