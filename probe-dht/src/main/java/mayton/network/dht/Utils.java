package mayton.network.dht;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

/**
 *
 * Example:
 *
 * magnet:?xt=urn:ed2k:354B15E68FB8F36D7CD88FF94116CDC1
 * &xl=10826029
 * &dn=mediawiki-1.15.1.tar.gz
 * &xt=urn:tree:tiger:7N5OAMRNGMSSEUE3ORHOKWN4WWIQ5X4EBOOTLJY
 * &xt=urn:sha1:XRX2PEFXOOEJFRVUCX6HMZMKS5TWG4K5
 * &xt=urn:aich:7ZDRR3ZQW4JMHUQZUMJGQN2VNGLV3CVN
 * &xt=urn:btih:QHQXPYWMACKDWKP47RRVIV7VOURXFE5Q
 * &tr=http%3A%2F%2Ftracker.example.org%2Fannounce.php%3Fuk%3D1111111111%26
 * &tr=wss%3A%2F%2Ftracker.webtorrent.io
 * &as=http%3A%2F%2Fdownload.wikimedia.org%2Fmediawiki%2F1.15%2Fmediawiki-1.15.1.tar.gz
 * &ws=http%3A%2F%2Fdownload.wikimedia.org%2Fmediawiki%2F1.15%2Fmediawiki-1.15.1.tar.gz
 * &xs=http%3A%2F%2Fcache.example.org%2FXRX2PEFXOOEJFRVUCX6HMZMKS5TWG4K5
 * &xs=dchub://example.org
 */
public class Utils {

    public static String wrapValue(Object value) {
        if (value instanceof byte[]) {
            StringBuilder sb = new StringBuilder();
            byte[] buf = (byte[]) value;
            for (byte b : buf) {
                int unsigned = b < 0 ? (int) b + 128 : (int)b;
                sb.append(String.format("%02X ", unsigned));
            }
            return sb.toString();
        } else {
            return "'" + String.valueOf(value) + "'";
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
        for(Map.Entry<String, Object> item : res.entrySet()) {
            s.append(indent(offset));
            if (item.getValue() instanceof HashMap) {
                s.append(dumpDEncodedMap((Map<String, Object>) item.getValue(), offset + 4));
            } else {
                s.append(item.getKey()).append(" : ").append(wrapValue(item.getValue()));
            }
        }
        s.append("\n");
        return s.toString();
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
