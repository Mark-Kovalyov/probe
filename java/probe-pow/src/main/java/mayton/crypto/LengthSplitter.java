package mayton.crypto;

import org.apache.commons.codec.binary.Hex;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;
import org.apache.hadoop.util.hash.Hash;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class LengthSplitter {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("sensitive.properties"));
        GZIPInputStream inputStream = new GZIPInputStream(new FileInputStream(properties.getProperty("inputFile")));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String splitterPrefix = properties.getProperty("splitterPrefix");

        int MIN_LENGTH = 8;
        int MAX_LENGTH = 26;
        int cnt = 0;
        PrintWriter[] printWriter = new PrintWriter[MAX_LENGTH - MIN_LENGTH + 1];
        for (int i = MIN_LENGTH; i <= MAX_LENGTH; i++) {
            printWriter[cnt++] = new PrintWriter(splitterPrefix + i + ".csv");
        }
        cnt--;
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+");
        long maxRows = Long.MAX_VALUE;
        long current = 0;
        long count = 0;
        String line;
        while ((line = bufferedReader.readLine()) != null && current < maxRows) {
            int length = line.length();
            if (length >= MIN_LENGTH && length <= MAX_LENGTH) {
                if (pattern.matcher(line).matches()) {
                    int nStream = length - MIN_LENGTH;
                    printWriter[nStream].println(line);
                }
            }
            current++;
        }

        bufferedReader.close();
        while(cnt > 0) {
            printWriter[cnt--].close();
        }
    }


}
