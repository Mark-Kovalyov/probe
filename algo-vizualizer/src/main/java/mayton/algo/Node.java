package mayton.algo;

import mayton.image.Point;

import java.awt.*;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

public class Node {
    public StampedLock stampedLock;
    public List<Edge> outgoingEdges;
    public Node backwardNode;
    // Vizualization attributes
    public transient Point point;
    public transient Color color;
}
