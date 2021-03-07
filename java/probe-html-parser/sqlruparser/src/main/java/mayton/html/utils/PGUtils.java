package mayton.html.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import javax.annotation.concurrent.ThreadSafe;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;

@ThreadSafe
public class PGUtils {

    static Logger logger = LogManager.getLogger(PGUtils.class);

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

    @Nullable
    public static Date toJavaSqlDate(@Nullable LocalDateTime registered) {
        if (registered == null) {
            return null;
        }
        logger.trace(":: registered = {}", registered.toString());
        Instant instant = registered.toInstant(ZoneOffset.UTC);
        logger.trace(":: Epoch mili-second = {}", instant.getEpochSecond() * 1000);
        return new Date(instant.getEpochSecond() * 1000);
    }
}
