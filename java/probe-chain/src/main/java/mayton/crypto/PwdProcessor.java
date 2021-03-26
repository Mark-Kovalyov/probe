package mayton.crypto;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.tuple.Pair;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class PwdProcessor implements Runnable {

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

    public PwdProcessor(BlockingQueue<String> bq, PrintWriter printWriter, String alphabet, int max, int chainLength, MessageDigest messageDigestFunc) {
        this.bq = bq;
        this.printWriter = printWriter;
        this.alphabet = alphabet;
        this.max = max;
        this.chainLength = chainLength;
        this.messageDigest = messageDigestFunc;
    }

    public PwdProcessor(BlockingQueue<String> bq, Consumer<Pair<String, String>> recordConsumer, String alphabet, int max, int chainLength,
                        MessageDigest messageDigestFunc, BiFunction<Pair<String, String>, Integer, String> revPwdFunc) {
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
