package mayton.html.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import javax.annotation.concurrent.ThreadSafe;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

@ThreadSafe
public class PGUtils {

    private PGUtils() {
    }

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
    @NotNull
    public static String mapToJson(@NotNull LinkedHashMap<Integer, Double> messagesDistibution) {
        JSONObject jsonObject = new JSONObject();
        messagesDistibution.forEach((key, value) -> jsonObject.put(String.valueOf(key), (double) value));
        return jsonObject.toString();
    }
}
