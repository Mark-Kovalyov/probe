package mayton.crypto;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.io.FileInputStream;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;

import java.util.Properties;
import java.util.stream.Stream;

import static mayton.crypto.Utils.*;


public class RainbowGen {

    public static void main(String[] args) throws Exception {
        int t = 7; // Chain length
        int m = 16; // Chains in table
        int pwdLength = 12; // chars (by modulo 3)
        int limitHash = 3 * pwdLength / 4; // (by modulo 4)
        Properties properties = new Properties();
        properties.load(new FileInputStream("sensitive.properties"));
        String pwds = properties.getProperty("pwds");
        try (Stream<String> stream = Files.lines(Paths.get(pwds))) {
            stream.limit(m).forEach(line -> {
                print(line);
                print(" --> ");
                byte[] hash = getShaFromLine(line);
                for (int i = 0; i < ( t / 2 ); i++) {
                    //print(Hex.encodeHexString(hash));
                    print(Utils.encodeBase64(hash));
                    print(" --> ");
                    String reduct = Utils.encodeBase64(subArray(hash, 0, limitHash));
                    print(reduct);
                    if (i < t / 2 - 1) {
                        print(" --> ");
                        hash = getShaFromLine(reduct);
                    }
                }
                print("\n");
            });
        }
    }

}
