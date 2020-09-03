package mayton.compression.encoders.fibonacci;

import org.apache.commons.lang3.StringUtils;
import org.checkerframework.common.value.qual.IntRange;
import org.jetbrains.annotations.NotNull;

public class FibonacciUtils {

    private FibonacciUtils() {
    }

    @IntRange(from = 0)
    public static long decodeF(@NotNull String s) {
        if (s.length() == 0) {
            throw new IllegalArgumentException("Unable to decode empty string!");
        }
        // TODO: Check for maximum possible value for s
        long a = 1;
        long b = 1;
        long t;
        long sum = 0;
        char prevChar = '*';
        for(int i = 0; i < s.length(); i++ ) {
            char c = s.charAt(i);
            if (c == '1') {
                sum += b;
            } else if (c != '0') {
                throw new IllegalArgumentException("Unexpected symbol found " + Character.toString(c));
            }
            t = a + b;
            a = b;
            b = t;
            if (prevChar == '1' && c == '1') {
                throw new IllegalArgumentException("Illegal Fibonaccy notation. Impossible to have '11' sequence in " + s);
            }
            prevChar = c;
        }
        return sum;
    }

    @NotNull
    public static String encodeF(@IntRange(from = 1) long n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Unable to encode '0' or negative");
        }
        long a = 1;
        long b = 1;
        long t;
        long len = 0;
        while (b <= n) {
              t = a + b;
              a = b;
              b = t;
              len++;
        }
        StringBuilder s = new StringBuilder();
        while(len > 0) {
              t = b - a;
              b = a;
              a = t;
              len--;
              if (b <= n) {
                  s.append("1");
                  n -= b;
              } else {
                  s.append("0");
              }
        }
        return StringUtils.reverse(s.toString());
    }

}
