package mayton.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

public interface GenericCompressor {

    void compress(@NotNull Reader in, @NotNull OutputStream out) throws IOException;

}
