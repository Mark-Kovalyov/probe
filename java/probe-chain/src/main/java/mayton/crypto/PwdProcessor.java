package mayton.crypto;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class PwdProcessor implements Runnable {

    static Logger logger = LoggerFactory.getLogger(PwdProcessor.class);

    static private MessageDigest sha256;

    private BlockingQueue<String> bq;
    private PrintWriter printWriter;
    private String alphabet;
    private int max;
    private int chainLength;
    private MessageDigest messageDigest;

    static {
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    public PwdProcessor(BlockingQueue<String> bq, PrintWriter printWriter, String alphabet, int max, int chainLength,
                        MessageDigest messageDigestFunc) {
        this.bq = bq;
        this.printWriter = printWriter;
        this.alphabet = alphabet;
        this.max = max;
        this.chainLength = chainLength;
        this.messageDigest = messageDigestFunc;
    }

    public PwdProcessor(BlockingQueue<String> bq, Consumer<Pair<String, String>> recordConsumer, String alphabet,
                        int max, int chainLength, MessageDigest messageDigestFunc,
                        BiFunction<Pair<String, String>, Integer, String> revPwdFunc) {
        this.bq = bq;
        this.alphabet = alphabet;
        this.max = max;
        this.chainLength = chainLength;
        this.messageDigest = messageDigestFunc;
    }

    public static String hashPwd(String pwd) {
        sha256.reset();
        sha256.update(pwd.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(sha256.digest());
    }

    public static String reversePwd(String hexEncodedHash, String alphabet, int max) {
        BigInteger numericOfHash = new BigInteger(hexEncodedHash, 16); // radix = [2..36]
        BigInteger divider = BigInteger.valueOf(alphabet.length());
        StringBuilder sb = new StringBuilder(max);
        for(int i = 0; i < max; i++) {
            int mod = numericOfHash.mod(divider).intValue();
            sb.append(alphabet.charAt(mod));
            numericOfHash = numericOfHash.divide(divider);
        }
        return sb.toString();
    }

    public static String reversePwd2(String hexEncodedHash, String alphabet, int max) {
        BigInteger numericOfHash = new BigInteger(hexEncodedHash, 16); // radix = [2..36]
        BigInteger divider = BigInteger.valueOf(alphabet.length());
        StringBuilder sb = new StringBuilder(max);
        for(int i = 0; i < max; i++) {
            BigInteger[] res = numericOfHash.divideAndRemainder(divider);
            int mod = res[1].intValue();
            sb.append(alphabet.charAt(mod));
            numericOfHash = res[0];
        }
        return sb.toString();
    }

    /**
     * <pre>
     * \begin{align*}
     * \log_x{y} = \frac{\ln x}{\ln y}
     * \end{align*}
     * </pre>
     *
     * @param hexEncodedHash
     * @param alphabet
     * @param max
     * @return
     */
    public static String reversePwd3(String hexEncodedHash, String alphabet, int max) {
        Validate.notEmpty(hexEncodedHash);
        Validate.notEmpty(alphabet);
        Validate.isTrue(max > 0 && max < 100, "The password length");
        Validate.isTrue(alphabet.length() > 1, "Alphabet must not be empty");
        Validate.isTrue(hexEncodedHash.length() == sha256.getDigestLength() * 2, "hexEncodedHash length " + hexEncodedHash.length() + " must be equals to " + 2 * sha256.getDigestLength());
        logger.info("sha256.getDigestLength() = {} bytes ({} bits, {} binhex chars)", sha256.getDigestLength(), 8 * sha256.getDigestLength(), 2 * sha256.getDigestLength());
        BigInteger numericOfHash = new BigInteger(hexEncodedHash, 16); // radix = [2..36]
        int reasonDigits = 1 + (int) (Math.pow(alphabet.length(), max));
        BigInteger divider = BigInteger.valueOf(alphabet.length());
        StringBuilder sb = new StringBuilder(max);
        for(int i = 0; i < max; i++) {
            BigInteger[] res = numericOfHash.divideAndRemainder(divider);
            int mod = res[1].intValue();
            //logger.debug("{} {} {}", i, numericOfHash.toString(10), mod);
            sb.append(alphabet.charAt(mod));
            numericOfHash = res[0];
            Validate.isTrue(numericOfHash.compareTo(divider) > 0, "The current hash value " + numericOfHash.toString() + " must be greather than divider for every iteration!");
        }
        return sb.toString();
    }

    @Override
    public void run() {
        try {
            String pwd;
            while (true) {
                pwd = bq.poll(1, TimeUnit.SECONDS);
                if (pwd.equals("\uFFFF")) break;
                printWriter.printf("%s ", pwd);
                for (int i = 0; i < chainLength; i++) {
                    messageDigest.reset();
                    messageDigest.update(pwd.getBytes(StandardCharsets.UTF_8));
                    byte[] digest = messageDigest.digest();
                    String digestString = Hex.encodeHexString(digest);
                    pwd = reversePwd(digestString, alphabet, max);
                }
                printWriter.printf("%s\n", pwd);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
