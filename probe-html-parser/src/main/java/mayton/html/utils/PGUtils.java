package mayton.html.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.concurrent.ThreadSafe;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.StringJoiner;

@ThreadSafe
public class PGUtils {

    private PGUtils() {}

    public static void safeClose(@Nullable Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            // Nothing to do
        }
    }

    @SuppressWarnings("java:S1319")
    public static String mapToJson(@NotNull LinkedHashMap<Integer, Double> messagesDistibution) {
        StringJoiner stringJoiner = new StringJoiner(",","{","}");
        messagesDistibution.forEach((key,value) ->
            stringJoiner.add("\"" + key + "\" : " + value)
        );
        return stringJoiner.toString();
    }
}
