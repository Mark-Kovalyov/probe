package mayton.semanticweb;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.replace;

public class Utils {

    private Utils() {}

    private static Set<String> sqlReservedWords = Sets.newHashSet("type", "table", "view");

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
        return replace(replace(arg, "\n", "\\u0D"), "'", "\\u27");
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
    public static String filterNamespaces(@Nonnull String arg) {

        if (arg.startsWith("http://permid.org/ontology/organization/")) {
            return arg.substring(40);
        } else if (arg.startsWith("http://www.w3.org/2006/vcard/ns#")) {
            return arg.substring(32);
        } else if (arg.startsWith("http://permid.org/ontology/financial/")) {
            return arg.substring(37);
        } else if (arg.startsWith("https://permid.org/")) {
            return arg.substring(19);
        } else if (arg.startsWith("http://sws.geonames.org/")){
            return arg.substring(24);
        } else {
            return arg;
        }
    }

}
