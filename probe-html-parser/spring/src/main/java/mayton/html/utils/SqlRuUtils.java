package mayton.html.utils;

import mayton.html.HtmlParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.replace;
import static org.apache.commons.lang3.StringUtils.remove;

public class SqlRuUtils {

    private SqlRuUtils() {
        // No constructor
    }

    static Logger logger = LogManager.getLogger(SqlRuUtils.class);

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static double parsePecentFormattedValue(@NotNull String value) {
        return Double.parseDouble(remove(replace(value,",","."),"%"));
    }

    static final Map<String, String> map = new HashMap() {{
        put("января", "JAN");
        put("октября", "OCT");
        put("сентября", "SEP");
        put("ноября", "NOV");
        put("декабря", "DEC");
        put("марта", "MAR");
        put("апреля", "APR");
        put("мая", "MAY");
        put("июня", "JUN");
        put("июля", "JUL");
        put("августа", "AUG");
        put("февраля", "FEB");
    }};

    static final String DATE_FORMAT_PATTERN = "d MMM yyyy k:m";

    static Pattern DATE_RUS_REGEX_PATTERN = Pattern.compile("(?<day>\\d{1,2}) (?<monthRus>[А-яЁё][-А-яЁё]+) (?<year>\\d{4}), (?<hourMinutes>\\d{1,2}:\\d{1,2}).+");
    static Pattern DATE_RUS_NOW_REGEX_PATTERN = Pattern.compile("сегодня, (?<hours>\\d{1,2}):(?<minutes>\\d{1,2}).+");
    static Pattern DATE_RUS_YESTERDAY_REGEX_PATTERN = Pattern.compile("вчера, (?<hours>\\d{1,2}):(?<minutes>\\d{1,2}).+");

    // 23 октября 2002, 09:43
    // 'сегодня, 15:29 (0 дней 0 часов 7 минут назад)'
    // 'вчера, 13:26 (1 дней 2 часов 53 минут назад)'
    @NotNull
    public static LocalDateTime parseSqlRuDate(@NotNull String inputDate) {
        logger.trace(":::[1]Trying to parse '{}' with {}", inputDate, DATE_RUS_REGEX_PATTERN.toString());
        Matcher matcher  = DATE_RUS_REGEX_PATTERN.matcher(inputDate);
        Matcher matcher2 = DATE_RUS_NOW_REGEX_PATTERN.matcher(inputDate);
        Matcher matcher3 = DATE_RUS_YESTERDAY_REGEX_PATTERN.matcher(inputDate);
        if (matcher.matches()) {
            logger.trace(":::[2]");
            String day = matcher.group("day");
            String mon = map.get(matcher.group("monthRus"));
            String year = matcher.group("year");
            String hourMinutes = matcher.group("hourMinutes");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
            String transformed = day + " " + mon.substring(0,1) + mon.substring(1,3).toLowerCase() + " " + year + " " + hourMinutes;
            LocalDateTime result = LocalDateTime.parse(transformed, formatter);
            logger.trace(":::[2.1] {}", result.format(DateTimeFormatter.ISO_DATE_TIME));
            return result;
        } else if (matcher2.matches()) {
            logger.trace(":::[3.1]");
            LocalTime localTime = LocalTime.of(
                    parseInt(matcher2.group("hours")),
                    parseInt(matcher2.group("minutes")),
                    0);

            return LocalDate.now().atTime(localTime);
        } else if (matcher3.matches()) {
            logger.trace(":::[4.1]");
            LocalTime localTime = LocalTime.of(
                    parseInt(matcher3.group("hours")),
                    parseInt(matcher3.group("minutes")),
                    0);

            return LocalDate.now().minusDays(1).atTime(localTime);
        } else {
            throw new HtmlParserException("Unable to parse '" + inputDate + "' with patterns " + DATE_RUS_REGEX_PATTERN + " and " + DATE_RUS_NOW_REGEX_PATTERN);
        }
    }
}
