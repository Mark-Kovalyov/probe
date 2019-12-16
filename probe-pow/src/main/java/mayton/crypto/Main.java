package mayton.crypto;

import org.apache.commons.codec.binary.Hex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class Main {

    // Facts:
    // Size gzippped = 8G
    // Size = ?
    // Rows = 1 206 769 972
    // SHA1 = 160 bit = 20 bytes = 40 chars
    //
    // Hashed 1.2 bill rows = (20 * 1206769972) / 1024 / 1024 / 1024 = 22G
    //
    // Bloom Size : 0.7 * m = n
    // m = 1206769972 keys
    // n = 0.7 * 1206769972 = 844738980 bits = 105592372 bytes = 100 Mb

    public static void main(String[] args) throws Exception {
        GZIPInputStream inputStream = new GZIPInputStream(new FileInputStream("/db/1.2billion/1.2billion.txt.gz"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+");
        int maxRows = 10000;
        int ignoredRows = 0;
        int current = 0;
        while ((line = bufferedReader.readLine()) != null && current < maxRows) {
            if (pattern.matcher(line).matches()) {
                messageDigest.reset();
                messageDigest.update(line.getBytes(StandardCharsets.UTF_8));
                byte[] result = messageDigest.digest();
                System.out.printf("%s %s\n", line, Hex.encodeHexString(result));
            } else {
                ignoredRows++;
            }
            current++;
        }

        bufferedReader.close();
    }


}
