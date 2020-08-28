package mayton.compression;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

import static java.lang.String.format;

public class EncodingTools {

    public static LinkedHashMap<String, Object> quadroMap(String key1, Object value1, String key2, Object value2) {
        LinkedHashMap<String, Object> temp = new LinkedHashMap<>(2);
        temp.put(key1,value1);
        temp.put(key2,value2);
        return temp;
    }

    @NotNull
    public static String escape(@NotNull String arg) {
        StringBuilder sb = new StringBuilder(arg.length());
        for (int i = 0; i < arg.length(); i++) {
            int c = arg.charAt(i);
            if (c < 32) {
                if (c == '\n') {
                    sb.append("\\x").append(format("%02X", c));
                } else if (c == '\r') {
                    sb.append("\\x").append(format("%02X", c));
                } else if (c == '\t') {
                    sb.append("\\x").append(format("%02X", c));
                } else {
                    sb.append("\\x").append(format("%02X", c));
                }
            } else if (c < 128) {
                if (c == '\'') {
                    sb.append("\\x27");
                } else if (c == '\"') {
                    sb.append("\\x22");
                } else {
                    sb.append((char) c);
                }
            } else {
                sb.append((char) c);
            }
        }
        return sb.toString();
    }

}
