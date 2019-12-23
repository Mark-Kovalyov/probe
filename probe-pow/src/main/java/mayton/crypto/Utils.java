package mayton.crypto;

import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;
import javax.annotation.concurrent.NotThreadSafe;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@NotThreadSafe
public class Utils {

    static MessageDigest messageDigest = null;
    static Base64 base64encoder = new Base64();

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Utils() {}

    @NotNull
    public static byte[] subArray(byte[] array, int beg, int end) {
        return Arrays.copyOfRange(array, beg, end);
    }

    public static void print(@NotNull String arg) {
        System.out.print(arg);
    }

    @NotNull
    public static byte[] getShaFromLine(@NotNull String line) {
        messageDigest.reset();
        messageDigest.update(line.getBytes(StandardCharsets.UTF_8));
        return messageDigest.digest();
    }

    @NotNull
    public static String encodeBase64(@NotNull byte[] arr) {
        return base64encoder.encodeAsString(arr);
    }

}
