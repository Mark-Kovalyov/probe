package mayton.compression.graphs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Vertex implements Serializable {

    static final long serialVersionUID = 1L;

    // Key fields
    private String id;

    // Non-key fields
    private List<Edge> outgoingEdges = new ArrayList<>();
    private List<Edge> incomingEdges = new ArrayList<>();

    public Vertex(String id) {
        this.id = id;
    }

    public Vertex() {
        // For serialization
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(id, vertex.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public void setOutgoingEdges(List<Edge> outgoingEdges) {
        this.outgoingEdges = outgoingEdges;
    }

    @Override
    public String toString() {
        return String.format("Vertex id = %s, with outgoing vertices : %s", id,
                outgoingEdges.stream()
                .map(edge -> edge.getV2().id)
                .collect(Collectors.joining(",")));
    }

    public List<Edge> getIncomingEdges() {
        return incomingEdges;
    }
}
