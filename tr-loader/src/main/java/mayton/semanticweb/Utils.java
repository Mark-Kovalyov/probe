package mayton.semanticweb;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.Set;

import static java.lang.String.format;
import static mayton.semanticweb.Constants.*;
import static org.apache.commons.lang3.StringUtils.replace;

public class Utils {

    private Utils() {}

    private static Set<String> sqlReservedWords = Sets.newHashSet("type", "table", "view", "from", "to", "rank");

    @Nonnull
    public static String formatFieldName(@Nonnull String arg) {
        if (sqlReservedWords.contains(arg.toLowerCase())) {
            return "\"" + arg.toUpperCase() + "\"";
        } else {
            return replace(CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, arg), "-", "_");
        }
    }

    @Nonnull
    public static String wrapPostgresLiteral(@Nonnull String arg) {
        StringBuilder sb = new StringBuilder(arg.length() + 2);
        sb.append("'");
        for(int i = 0 ; i < arg.length(); i++) {
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
                if (c=='\'') {
                    sb.append("\\x27");
                } else {
                    sb.append((char)c);
                }
            } else if (c < 255) {
                sb.append("\\x").append(format("%02X", c));
            } else {
                sb.append("\\u").append(format("%04X", c));
            }
        }
        sb.append("'");
        return sb.toString();
    }

    @Nonnull
    public static String trimQuotes(@Nonnull String arg) {
        return StringUtils.strip(arg,"\"");
    }

    @Nonnull
    public static String filterDateTime(@Nonnull String arg) {
        int res = arg.indexOf("^^<http://www.w3.org/2001/XMLSchema#dateTime>");
        if (res > 0) {
            return arg.substring(0, res);
        } else {
            return arg;
        }
    }

    @Nonnull
    public static String trimSlash(@Nonnull String value) {
        return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }

    @Nonnull
    public static String filterNamespaces(@Nonnull String arg) {
        if (arg.startsWith(HTTP_PERMID_ORG_ONTOLOGY_ORGANIZATION)) {
            return arg.substring(HTTP_PERMID_ORG_ONTOLOGY_ORGANIZATION.length());
        } else if (arg.startsWith(HTTP_WWW_W_3_ORG_2006_VCARD_NS)) {
            return arg.substring(HTTP_WWW_W_3_ORG_2006_VCARD_NS.length());
        } else if (arg.startsWith(HTTP_PERMID_ORG_ONTOLOGY_FINANCIAL)) {
            return arg.substring(HTTP_PERMID_ORG_ONTOLOGY_FINANCIAL.length());
        } else if (arg.startsWith(HTTPS_PERMID_ORG)) {
            return arg.substring(HTTPS_PERMID_ORG.length());
        } else if (arg.startsWith(HTTP_SWS_GEONAMES_ORG)){
            return arg.substring(HTTP_SWS_GEONAMES_ORG.length());
        } else {
            return arg;
        }
    }

}
