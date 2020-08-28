package mayton.compression;

import org.jetbrains.annotations.NotNull;

import java.io.Reader;

public interface GenericProcessor {

    @NotNull
    Object process(@NotNull Reader in);

}
