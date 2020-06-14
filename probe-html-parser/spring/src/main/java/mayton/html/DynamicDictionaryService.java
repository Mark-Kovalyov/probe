package mayton.html;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public interface DynamicDictionaryService {

    int getOrCreateEntityId(@NotNull String entityName) throws SQLException;

}
