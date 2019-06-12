package mayton.probe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import graphql.ExecutionResult;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class JsonKit {

    static final Gson GSON = new GsonBuilder().serializeNulls().create();


    @Nonnull
    public static void returnAsJson(@Nonnull HttpServletResponse response,@Nonnull ExecutionResult executionResult) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        JsonKit.toJson(response, executionResult.toSpecification());
    }

    public static void toJson(HttpServletResponse response, Object result) throws IOException {
        GSON.toJson(result, response.getWriter());
    }

    @Nonnull
    public static Map<String, Object> toMap(@Nullable String jsonStr) {

        if (jsonStr == null || jsonStr.trim().length() == 0) {
            return Collections.emptyMap();
        }

        // gson uses type tokens for generic input like Map<String,Object>
        TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {
        };

        Map<String, Object> map = GSON.fromJson(jsonStr, typeToken.getType());

        return map == null ? Collections.emptyMap() : map;
    }
}
