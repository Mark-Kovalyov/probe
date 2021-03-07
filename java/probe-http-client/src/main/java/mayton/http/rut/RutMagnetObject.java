package mayton.http.rut;

import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.Immutable;

@Immutable
public class RutMagnetObject {
    
    public final String magnet;
    public final String title;
    public final long size;

    public RutMagnetObject(@NotNull String magnet, @NotNull String title, long size) {
        this.magnet = magnet;
        this.title = title;
        this.size = size;
    }
}
