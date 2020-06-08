package mayton.html.utils;

import mayton.html.Forum;

import javax.annotation.concurrent.ThreadSafe;
import java.util.LinkedHashMap;
import java.util.StringJoiner;

@ThreadSafe
public class PGUtils {

    private PGUtils() {}

    public static String mapToJson(LinkedHashMap<Forum, Double> messagesDistibution) {
        StringJoiner stringJoiner = new StringJoiner("{", "}", ",");
        messagesDistibution.forEach((key,value) ->
            stringJoiner.add(key.id + " : " + value)
        );
        return stringJoiner.toString();
    }
}
