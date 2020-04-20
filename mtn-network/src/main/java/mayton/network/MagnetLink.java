package mayton.network;

import javax.annotation.concurrent.Immutable;

@Immutable
public class MagnetLink implements Link {

    private String name;
    private long size;

    public MagnetLink(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }
}
