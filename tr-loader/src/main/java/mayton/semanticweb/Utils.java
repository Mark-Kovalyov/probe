package mayton.semanticweb;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.vocabulary.XSD;

import javax.annotation.Nonnull;
import java.util.Set;

import static java.lang.String.format;
import static mayton.semanticweb.Constants.*;
import static org.apache.commons.lang3.StringUtils.indexOf;
import static org.apache.commons.lang3.StringUtils.replace;

public class Utils {

    private Utils() {
    }

    private static Set<String> sqlReservedWords = Sets.newHashSet("type", "table", "view", "from", "to", "rank");

    @Nonnull
    public static String filterSQLWordsAndDashStyle(@Nonnull String arg) {
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
                } else {
                    sb.append((char) c);
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
        return StringUtils.strip(arg, "\"");
    }

    @Nonnull
    public static String filterXmlSchemaTypes(@Nonnull String arg) {
        int res = arg.indexOf("\"^^<http://www.w3.org/2001/XMLSchema#");
        if (res > 0) {
            return arg.substring(0, res);
        } else {
            return arg;
        }
    }

    @Nonnull
    public static String filterFieldValue(@Nonnull String arg) {
        return wrapPostgresLiteral(
                trimSlash(
                        filterXmlSchemaTypes(
                                filterNamespaces(
                                        trimAllQuotesLeftAndRight(arg)))));
    }

    public static String filterFieldName(String arg) {
        return filterNamespaces(arg);
    }

    @Nonnull
    public static IRI detectXmlSchemaTypes(@Nonnull String arg) {
        // todo fix
        return XSD.STRING;
    }

    public static int lengthFrontSequenceOf(String value, char c) {
        int i = 0;
        while(value.charAt(i) == c) {
            i++;
        }
        return i;
    }

    public static int lengthBackSequenceOf(String value, char c) {
        int cnt = 0;
        if (value.length()==0) {
            return 0;
        }
        int i = value.length() - 1;
        while(value.charAt(i) == c) {
            cnt++;
            i--;
        }
        return cnt;
    }

    @Nonnull
    public static String trimAllQuotesLeftAndRight(@Nonnull String value) {
        return value.substring(
                lengthFrontSequenceOf(value,'\"'),
                value.length() - lengthBackSequenceOf(value, '\"')
        );
    }

    @Nonnull
    public static String trimSlash(@Nonnull String value) {
        return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }

    static final String RDFS = "rdfs:";

    public static String filterPrefix(@Nonnull String arg) {
        if (arg.startsWith(RDFS)) {
            return arg.substring(RDFS.length());
        } else {
            return arg;
        }
    }

    @Nonnull
    public static String filterNamespaces(@Nonnull String arg) {
        if (arg.startsWith("htt")) {
            if (arg.startsWith("http://www.w3.org/")) {
                if (arg.startsWith(W3ORG_2006)) {
                    return arg.substring(W3ORG_2006.length());
                } else if (arg.startsWith(W3ORG_1999)) {
                    return arg.substring(W3ORG_1999.length());
                } else if (arg.startsWith(W3ORG_2004)) {
                    return arg.substring(W3ORG_2004.length());
                } else if (arg.startsWith(W3ORG_2000)) {
                    return arg.substring(W3ORG_2000.length());
                } else {
                    return arg;
                }
            } else if (arg.startsWith("http://permid.org/ontology/")){
                if (arg.startsWith(ORGANIZATION)) {
                    return arg.substring(ORGANIZATION.length());
                } else if (arg.startsWith(FINANCIAL)) {
                    return arg.substring(FINANCIAL.length());
                } else if (arg.startsWith(PERMID)) {
                    return arg.substring(PERMID.length());
                } else if (arg.startsWith(COMMON)) {
                    return arg.substring(COMMON.length());
                } else if (arg.startsWith(CURRENCY)) {
                    return arg.substring(CURRENCY.length());
                } else if (arg.startsWith(VCARD)) {
                    return arg.substring(VCARD.length());
                } else if (arg.startsWith(PERSON)) {
                    return arg.substring(PERSON.length());
                } else if (arg.startsWith(MDAAS)) {
                    return arg.substring(MDAAS.length());
                } else {
                    return arg;
                }
            } else {
                if (arg.startsWith(HTTP_SWS_GEONAMES_ORG)) {
                    return arg.substring(HTTP_SWS_GEONAMES_ORG.length());
                } else if (arg.startsWith(PERMID)) {
                    return arg.substring(PERMID.length());
                } else if (arg.startsWith(OMG)) {
                    return arg.substring(OMG.length());
                } else {
                    return arg;
                }
            }
        } else {
            return arg;
        }
    }

}
