package mayton.probes;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class Edge {
    final String id;

    public Edge(String id) {
        this.id = id;
    }
}
