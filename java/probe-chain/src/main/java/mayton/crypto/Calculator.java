package mayton.crypto;

import org.apache.commons.io.FileUtils;

import java.math.BigInteger;

public class Calculator {

    public static void main(String[] args) {

        // qwerty -> 48567wop ; 5

        BigInteger pwdLen = BigInteger.valueOf(Main.MAX_PWD_LENGTH);
        BigInteger alphabet = BigInteger.valueOf(Main.ALPHABET.length());
        BigInteger chainLength = BigInteger.valueOf(Main.CHAIN_LENGTH);

        //
        BigInteger combinations = alphabet.pow(pwdLen.intValue());
        BigInteger dataRows = combinations.divide(chainLength);

        //
        System.out.printf("combinations = %s\n", combinations.toString());
        System.out.printf("disk space   = %s\n", FileUtils.byteCountToDisplaySize(dataRows.multiply(pwdLen).multiply(BigInteger.TWO)));
    }

}
