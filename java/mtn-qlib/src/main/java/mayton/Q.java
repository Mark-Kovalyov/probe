package mayton;

import org.jetbrains.annotations.NotNull;

import java.io.Externalizable;
import java.io.IOException;
import java.io.Serializable;

public interface Q {

    // Externalizable?
    void add(@NotNull Serializable entity) throws IOException;

    Serializable poll() throws IOException, ClassNotFoundException;

    void purge() throws IOException;

}
