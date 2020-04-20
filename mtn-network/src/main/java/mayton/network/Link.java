package mayton.network;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public interface Link {

    @NotNull String getName();

    @Range(from = 0, to = Long.MAX_VALUE) long getSize();

    @NotNull String formatLink();

}
