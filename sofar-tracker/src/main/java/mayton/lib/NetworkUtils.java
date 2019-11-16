package mayton.lib;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import static java.lang.Long.parseLong;

public class NetworkUtils {

    private NetworkUtils() {
        // no inst
    }


    public static long parseIpV4(@NotNull String ipv4) {
        if (ipv4 == null) {
            throw new IllegalArgumentException("Unable to parse null argument!");
        } else if (ipv4.length() < 7) {
            throw new IllegalArgumentException("Unable to parse " + ipv4 + " like an IPv4 address. Not enought string length");
        } else if (ipv4.length() > 15) {
            throw new IllegalArgumentException("Unable to parse " + ipv4 + " like an IPv4 address. String length too long");
        }
        String[] parts = StringUtils.split(ipv4, '.');
        if (parts.length != 4) {
            throw new IllegalArgumentException("Unable to parse " + ipv4 + " like an IPv4 address. Not enought digits");
        }
        try {
            return 256L * 256 * 256 * parseLong(parts[0]) +
                    256L * 256 * parseLong(parts[1]) +
                    256L * parseLong(parts[2]) +
                    parseLong(parts[3]);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Unable to parse " + ipv4 + " like an IPv4 address. Number format exception.");
        }
    }

}
