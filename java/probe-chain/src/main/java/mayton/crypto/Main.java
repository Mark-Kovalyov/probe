package mayton.crypto;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Hello world!
 *
 */
public class Main {

    public static String ALPHABET = "abcdefghjklmnopqrstuvwxyz0123456789";

    public static int MAX_PWD_LENGTH = 12;

    public static int CHAIN_LENGTH = 1000;

    public static void main( String[] args ) throws InterruptedException, IOException, NoSuchAlgorithmException {

        BlockingQueue<String> pwdGeneratorProcessorSink = new ArrayBlockingQueue<>(10000, true);

        PrintWriter printWriter = new PrintWriter(System.out);

        PwdGen pwdGen = new PwdGen(pwdGeneratorProcessorSink, ALPHABET, MAX_PWD_LENGTH);

        PwdProcessor pwdProcessor = new PwdProcessor(
                pwdGeneratorProcessorSink,
                printWriter,
                ALPHABET,
                MAX_PWD_LENGTH,
                CHAIN_LENGTH,
                MessageDigest.getInstance("SHA-256"));

        Thread pwdProcessorThread = new Thread(pwdProcessor);

        Thread pwdGenThread = new Thread(pwdGen);

        pwdGenThread.start();
        pwdProcessorThread.start();

        pwdGenThread.join();
        pwdProcessorThread.join();

        printWriter.close();

    }
}
