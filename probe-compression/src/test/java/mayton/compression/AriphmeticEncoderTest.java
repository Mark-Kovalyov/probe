package mayton.compression;

import mayton.compression.encoders.AriphmeticEncoder;
import org.apache.commons.lang3.tuple.Triple;

import java.math.BigInteger;

import static mayton.compression.encoders.AriphmeticEncoder.arithmeticCoding;
import static mayton.compression.encoders.AriphmeticEncoder.arithmeticDecoding;

public class AriphmeticEncoderTest {

    public static String decodeRange(BigInteger x) {
        if (x.compareTo(BigInteger.valueOf(256 - 1)) < 0) {
            return "byte";
        } else if (x.compareTo(BigInteger.valueOf(0xFFFF)) < 0) {
            return "word"; // short
        } else if (x.compareTo(BigInteger.valueOf(0xFFFF_FFFFL)) < 0) {
            return "dword"; // int
        } else if (x.compareTo(new BigInteger("18446744073709551615")) < 0) {
            return "qword"; // long
        } else {
            return "?"; // long
        }
    }

    public static void main(String[] args) {
        long radix = 10;

        String orignilal = "Ежели бы Наполеон  не оскорбился требованием  отступить  за  Вислу и не велел наступать  войскам,  не было бы войны";
        StringBuilder part = new StringBuilder();
        for(int i = 0; i < orignilal.length();i++) {
            part.append(orignilal.charAt(i));
            Triple<BigInteger, Integer, AriphmeticEncoder.Freq> encoded = arithmeticCoding(part.toString(), 10);
            String dec = arithmeticDecoding(encoded.getLeft(), radix, encoded.getMiddle(), encoded.getRight());
            System.out.printf("%s : dec = %s (datatype : %s)\n", encoded.getLeft(), dec, decodeRange(encoded.getLeft()));
        }
    }

}
