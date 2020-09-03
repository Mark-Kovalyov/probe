package mayton.compression.encoders;

import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * This is copied and upgraded from https://rosettacode.org/wiki/Arithmetic_coding/As_a_generalized_change_of_radix
 */
public class AriphmeticOutputStream extends CompactDigitOutputStream {

    public AriphmeticOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public static class Freq extends HashMap<Character, Long> {
        //"type alias"
    }

    private static Freq cumulativeFreq(Freq freq) {
        long total = 0;
        Freq cf = new Freq();
        for (int i = 0; i < 256; ++i) {
            char c = (char) i;
            Long v = freq.get(c);
            if (v != null) {
                cf.put(c, total);
                total += v;
            }
        }
        return cf;
    }

    @NotNull
    public static Triple<BigInteger, Integer, Freq> arithmeticCoding(@NotNull String str, long radix) {
        // Convert the string into a char array
        char[] chars = str.toCharArray();

        // The frequency characters
        Freq freq = new Freq();
        for (char c : chars) {
            if (!freq.containsKey(c))
                freq.put(c, 1L);
            else
                freq.put(c, freq.get(c) + 1);
        }

        // The cumulative frequency
        Freq cf = cumulativeFreq(freq);

        // Base
        BigInteger base = BigInteger.valueOf(chars.length);

        // LowerBound
        BigInteger lower = BigInteger.ZERO;

        // Product of all frequencies
        BigInteger pf = BigInteger.ONE;

        // Each term is multiplied by the product of the
        // frequencies of all previously occurring symbols
        for (char c : chars) {
            BigInteger x = BigInteger.valueOf(cf.get(c));
            lower = lower.multiply(base).add(x.multiply(pf));
            pf = pf.multiply(BigInteger.valueOf(freq.get(c)));
        }

        // Upper bound
        BigInteger upper = lower.add(pf);

        int powr = 0;
        BigInteger bigRadix = BigInteger.valueOf(radix);

        while (true) {
            pf = pf.divide(bigRadix);
            if (pf.equals(BigInteger.ZERO)) break;
            powr++;
        }

        BigInteger diff = upper.subtract(BigInteger.ONE).divide(bigRadix.pow(powr));

        return Triple.of(diff, powr, freq);
    }

    @NotNull
    public static String arithmeticDecoding(@NotNull BigInteger num, long radix, int pwr, Freq freq) {
        BigInteger powr = BigInteger.valueOf(radix);
        BigInteger enc = num.multiply(powr.pow(pwr));
        long base = 0;
        for (Long v : freq.values()) base += v;

        // Create the cumulative frequency table
        Freq cf = cumulativeFreq(freq);

        // Create the dictionary
        Map<Long, Character> dict = new HashMap<>();
        for (Map.Entry<Character, Long> entry : cf.entrySet()) dict.put(entry.getValue(), entry.getKey());

        // Fill the gaps in the dictionary
        long lchar = -1;
        for (long i = 0; i < base; ++i) {
            Character v = dict.get(i);
            if (v != null) {
                lchar = v;
            } else if (lchar != -1) {
                dict.put(i, (char) lchar);
            }
        }

        // Decode the input number
        StringBuilder decoded = new StringBuilder((int) base);
        BigInteger bigBase = BigInteger.valueOf(base);
        for (long i = base - 1; i >= 0; --i) {
            BigInteger pow = bigBase.pow((int) i);
            BigInteger div = enc.divide(pow);
            Character c = dict.get(div.longValue());
            BigInteger fv = BigInteger.valueOf(freq.get(c));
            BigInteger cv = BigInteger.valueOf(cf.get(c));
            BigInteger diff = enc.subtract(pow.multiply(cv));
            enc = diff.divide(fv);
            decoded.append(c);
        }
        // Return the decoded output
        return decoded.toString();
    }

}
