package mayton.probes;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class Vertex {
    final String id;

    public Vertex(String id) {
        this.id = id;
    }
}
