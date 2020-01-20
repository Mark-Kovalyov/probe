package mayton.algo;

import java.util.concurrent.locks.StampedLock;

public class Edge {

    public Node from;
    public Node to;

    public int flow;
    public int capacity;

    public Edge(Node from, Node to, int flow, int capacity) {
        this.from = from;
        this.to = to;
        this.flow = flow;
        this.capacity = capacity;
    }
}
