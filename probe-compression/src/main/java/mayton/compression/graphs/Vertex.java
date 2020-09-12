package mayton.compression.graphs;

import org.checkerframework.common.value.qual.IntRange;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;
import static java.util.Collections.unmodifiableList;

public class Vertex implements Serializable {

    static final long serialVersionUID = 1L;

    // Key fields
    private String id;

    // Non-key fields
    private List<Edge> outgoingEdges = new ArrayList<>();
    private List<Edge> incomingEdges = new ArrayList<>();

    public Vertex(@NotNull String id) {
        this.id = id;
    }

    public Vertex() {
        // For serialization
    }

    // Incoming edges

    void addIncomingEdge(@NotNull Edge edge) {
        checkArgument(edge.getV2() != this, "Unable to link edge " + edge.toString() + " because of illegal V2 value");
        incomingEdges.add(edge);
    }

    void addIncomingEdgeWithWeight(@NotNull Vertex sourceVertex, @IntRange(from = 0) int weight) {
        Edge incoming = new Edge(sourceVertex, this);
        incoming.setWeight(weight);
        incomingEdges.add(incoming);
    }

    void addIncomingEdge(@NotNull Vertex sourceVertex) {
        addIncomingEdgeWithWeight(sourceVertex, 0);
    }

    // Outgoing edges

    void addOutgoingEdge(@NotNull Edge edge) {
        checkArgument(edge.getV1() != this, "Unable to link edge " + edge.toString() + " because of illegal V1 value");
        outgoingEdges.add(edge);
    }

    void addOutgoingEdge(@NotNull Vertex destinationVertex) {
        addOutgoingEdgeWithWeight(destinationVertex, 0);
    }

    void addOutgoingEdgeWithWeight(@NotNull Vertex destinationVertex, @IntRange(from = 0) int weight) {
        Edge outgoing = new Edge(this, destinationVertex);
        outgoing.setWeight(weight);
        outgoingEdges.add(outgoing);
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

    public void setId(@NotNull String id) {
        this.id = id;
    }

    @NotNull
    public List<Edge> getOutgoingEdges() {
        return unmodifiableList(outgoingEdges);
    }

    @Override
    public String toString() {
        return format("Vertex id = %s, with incoming vertices : %s, and outgoing vertices : %s",
                id,
                incomingEdges.stream()
                    .map(edge -> edge.getV2().id)
                    .collect(Collectors.joining(",")),
                outgoingEdges.stream()
                    .map(edge -> edge.getV2().id)
                    .collect(Collectors.joining(",")));
    }

    @NotNull
    public List<Edge> getIncomingEdges() {
        return unmodifiableList(incomingEdges);
    }
}
