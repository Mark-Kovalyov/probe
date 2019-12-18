package mayton.finance;

import org.jetbrains.annotations.NotNull;

public class Cusip {

    public static boolean isDigit(int symbol) {
        return symbol >= (int) '0' && symbol <= (int) '9';
    }

    public static boolean isValid(@NotNull String cusip) {

        int d = 0;
        int sum = 0;
        int i = 0;
        boolean multiply = true;

        if (cusip.length() != 9 || !isDigit(cusip.charAt(8))) {
            return false;
        }

        for (sum = 0, multiply = true, i = 7; i > -1; --i) {
            if (i < 3) {
                if (isDigit(cusip.charAt(i))) {
                    d = cusip.charAt(i) - '0';
                } else {
                    return false;
                }
            } else {
                if (Character.isUpperCase((char) cusip.charAt(i))) {
                    d = cusip.charAt(i) - 'A' + 10;
                } else if (isDigit(cusip.charAt(i))) {
                    d = cusip.charAt(i) - '0';
                } else {
                    return false;
                }
            }

            if (multiply) {
                d *= 2;
            }
            multiply = !multiply;
            sum += (d / 10) + (d % 10);
        }

        sum %= 10;
        sum = 10 - sum;
        sum %= 10;

        if (sum != cusip.charAt(8) - '0') {
            return false;
        }

        return true;
    }

}
