package mayton.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

public interface GenericTextTransformer {

    void transform(@NotNull Reader in, @NotNull Writer out, @NotNull Properties properties) throws IOException;

    default void transform(@NotNull Reader in, @NotNull Writer out) throws IOException {
        transform(in, out, new Properties());
    }

}
