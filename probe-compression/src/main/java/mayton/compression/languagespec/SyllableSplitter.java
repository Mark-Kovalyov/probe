package mayton.compression.languagespec;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface SyllableSplitter {

    @NotNull List<String> split(@NotNull String arg);

}
