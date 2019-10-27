package mayton.semanticweb;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.replace;

public class Utils {

    private Utils() {}

    private static Set<String> sqlReservedWords = Sets.newHashSet("type", "table", "view");

    public static String formatFieldName(String arg) {
        if (sqlReservedWords.contains(arg.toLowerCase())) {
            return "\"" + arg.toUpperCase() + "\"";
        } else {
            return replace(CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, arg), "-", "_");
        }
    }

    public static String wrapPostgresLiteral(String arg) {
        return replace(replace(arg, "\n", "\\u0D"), "'", "\\u27");
    }

}
