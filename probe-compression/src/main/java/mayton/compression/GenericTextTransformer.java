package mayton.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public interface GenericTextTransformer {

    void transform(@NotNull Reader in,@NotNull Writer out) throws IOException;

}
