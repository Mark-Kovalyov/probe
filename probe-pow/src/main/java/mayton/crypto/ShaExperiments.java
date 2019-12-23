package mayton.crypto;

import org.apache.commons.codec.binary.Hex;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;
import org.apache.hadoop.util.hash.Hash;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class ShaExperiments {

    // Facts:
    // ======
    //
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
    //
    // Aftera hashing:
    //

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("sensitive.properties"));
        GZIPInputStream inputStream = new GZIPInputStream(new FileInputStream(properties.getProperty("inputFile")));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(properties.getProperty("outputFileSha1")));
        String line = null;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+");
        int maxRows = 100_000_000;
        int ignoredRows = 0;
        int current = 0;
        int bloomDuplicates = 0;
        // TODO calculate!
        int nbHash = 13;
        BloomFilter bloomFilter = new BloomFilter(1_206_769_972, nbHash, Hash.MURMUR_HASH);
        while ((line = bufferedReader.readLine()) != null && current < maxRows) {
            if (pattern.matcher(line).matches()) {
                messageDigest.reset();
                messageDigest.update(line.getBytes(StandardCharsets.UTF_8));
                byte[] result = messageDigest.digest();
                printWriter.printf("%s %s\n", Hex.encodeHexString(result), line);
                Key key = new Key(result);
                if (bloomFilter.membershipTest(key)) {
                    System.err.printf("Bloom collision detected on key = %s", Hex.encodeHexString(result));
                    bloomDuplicates++;
                }
                bloomFilter.add(key);
            } else {
                ignoredRows++;
            }
            current++;
        }

        bufferedReader.close();
    }


}
