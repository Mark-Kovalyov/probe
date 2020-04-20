package mayton.network;

import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.Immutable;

@Immutable
public class Ed2kLink implements Link {

    private String name;
    private long size;

    public Ed2kLink(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    @Override
    public @NotNull String formatLink() {
        return null;
    }
}
