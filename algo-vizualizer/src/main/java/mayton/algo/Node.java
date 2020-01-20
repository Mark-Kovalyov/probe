package mayton.algo;

import mayton.image.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

public class Node {

    public String name;
    public StampedLock stampedLock;
    public List<Edge> outgoingEdges;
    // public Node backwardNode;
    // Vizualization attributes
    public transient Point point;

    public Node(String name) {
        this.name = name;
        this.stampedLock = new StampedLock();
        this.outgoingEdges = new ArrayList<>();
    }
}
