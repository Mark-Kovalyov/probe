package mayton.compression.graphs;

import org.checkerframework.common.value.qual.IntRange;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Objects;

/**
 * Directed, Weight Edge
 */
public class Edge implements Serializable, Externalizable {

    static final long serialVersionUID = 2L;

    // Key fields
    private Vertex v1;
    private Vertex v2;

    // Non-key
    private int weight;

    public Edge(@NotNull Vertex v1, @NotNull Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Edge(@NotNull Vertex v1, @NotNull Vertex v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(@IntRange(from = 0) int weight) {
        this.weight = weight;
    }

    public Vertex getV1() {
        return v1;
    }

    public void setV1(@NotNull Vertex v1) {
        this.v1 = v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public void setV2(@NotNull Vertex v2) {
        this.v2 = v2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge edge = (Edge) o;

        if (Objects.equals(v1, edge.v1) && Objects.equals(v2, edge.v2)) {
            return true;
        }

        return Objects.equals(v1.getId(), edge.v1.getId())
                && Objects.equals(v2.getId(), edge.v2.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1.getId(), v2.getId());
    }

    @Override
    public String toString() {
        return String.format("Edge: (%s -> %s) , weight = %s", v1.getId(), v2.getId(), weight);
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeChars(v1.getId());
        objectOutput.writeChars(v2.getId());
        objectOutput.writeInt(weight);
        // TODO: Test
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        // TODO: Implement
    }
}
