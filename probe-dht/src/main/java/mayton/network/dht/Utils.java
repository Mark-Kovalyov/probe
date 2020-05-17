package mayton.network.dht;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class Utils {

    public static String wrapValue(Object value) {
        if (value instanceof byte[]) {
            StringBuilder sb = new StringBuilder("'");
            byte[] buf = (byte[]) value;
            boolean first = true;
            for (byte b : buf) {
                int byteValue = b < 0 ? (int) b + 128 : (int)b;
                if (!first) sb.append(' ');
                sb.append(format("%02X",byteValue));
                first = false;
            }
            sb.append("'");
            return sb.toString();
        } else {
            return "'" + value.toString() + "'";
        }
    }

    public static String indent(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<n;i++) sb.append(" ");
        return sb.toString();
    }

    @NotNull
    public static String dumpDEncodedMap(Map<String, Object> res, int offset) {
        StringBuilder s = new StringBuilder();
        s.append(" { ");
        boolean first = true;
        for(Map.Entry<String, Object> item : res.entrySet()) {
            if (!first) s.append(",");
            if (item.getValue() instanceof HashMap) {
                s.append("\n" + indent(offset) + " { 'hashMap' : ")
                 .append(dumpDEncodedMap((Map<String, Object>) item.getValue(), offset + 4))
                 .append("\n" + indent(offset) + " } ");
            } else {
                s.append("'" + item.getKey() + "'")
                 .append(" : ")
                 .append(wrapValue(item.getValue()));
            }
            first = false;
        }
        s.append(" } ");
        return s.toString();
    }

    @NotNull
    public static String binhex(@NotNull byte[] data) {
        return binhex(data, false);
    }

    @NotNull
    public static String binhex(@NotNull byte[] data, boolean truncateZeroes) {
        String splitter = " : ";
        StringBuilder sb = new StringBuilder(data.length * 2 + 1);
        sb.append("\n");
        int cnt = 0;
        int k = data.length - 1;
        if (truncateZeroes) {
            while(data[k] == 0 && k > 0) k--;
        }
        StringBuilder chars = new StringBuilder();
        StringBuilder bytes = new StringBuilder();
        int offset = 0;
        for (int i = 0; i < k; i++) {
            bytes.append(format("%02X ",data[i]));
            chars.append(data[i] >= 32 ? (char) data[i] : ' ');
            cnt++;
            if (cnt > 16) {
                cnt = 0;
                sb.append(format("%08X : ", offset))
                        .append(bytes)
                        .append(splitter)
                        .append(chars)
                        .append("\n");
                chars = new StringBuilder();
                bytes = new StringBuilder();
                offset += 16;
            }
        }
        if (cnt > 0) {
            sb.append(format("%08X : ", offset))
                    .append(bytes)
                    .append(String.join("", Collections.nCopies(17 - cnt, "   ")))
                    .append(splitter)
                    .append(chars)
                    .append("\n");
        }
        return sb.toString();
    }


}
