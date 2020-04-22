package mayton.network;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.annotation.concurrent.ThreadSafe;

import java.util.BitSet;

import static java.lang.Long.parseLong;

@ThreadSafe
public class NetworkUtils {

    private NetworkUtils() {
        // no inst
    }

    @NotNull
    public static String formatIpV4(@Range(from = 0, to = 4294967295L) long ipv4) {
        return "" + ((ipv4 & 0xFF_00_00_00L) >> 24) + "." +
                  + ((ipv4 & 0xFF_00_00) >> 16) + "." +
                  + ((ipv4 & 0xFF_00) >> 8) + "." +
                  + (ipv4 & 0xFF);
    }

    @NotNull
    public static String formatIpV6(long left, long right) {
        // TODO:
        throw new RuntimeException("Not implemented yet");
    }

    @Range(from = 0, to = 4294967295L)
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

    @NotNull
    public static BitSet parseIpV6(@NotNull String ipv6) {
        // TODO:
        throw new RuntimeException("Not implemented yet");
    }

}

