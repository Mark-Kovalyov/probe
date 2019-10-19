package mayton.ignite;

import jdk.internal.jline.internal.InputStreamReader;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.function.Predicate;

// Fast And Lazy CSV parser (FALC-S
public interface FastCSV {

    void skip(int lines);

    int getColumnsSize();

    boolean nextLine();

    String readString(int column);

    double readDouble(int column);

    int readInt(int column);

    long readLong(int column);
}
