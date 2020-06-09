package mayton.html;

import org.jetbrains.annotations.NotNull;

public interface DynamicDictionaryService {

    int getOrCreateEntityId(@NotNull String entityName);

}
