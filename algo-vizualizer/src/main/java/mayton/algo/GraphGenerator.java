package mayton.algo;

import mayton.image.Point;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

public class GraphGenerator {

    static Properties props=new Properties();

    static {
        try {
            props.load(new FileInputStream("sensitive.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportToGraphviz(Graph graph) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(props.getProperty("dest.graph")));
        pw.println("digraph {\n" +
                "    graph [pad=\"0.212,0.055\" bgcolor=lightgray  splines=line]\n" +
                "    node [style=filled]");
        graph.nodeList.stream().forEach(node -> {
            pw.printf("a [fillcolor=\"#d62728\" pos=\"%d,%d!\"]", node.point.x, node.point.y);
        });


        pw.close();
    }

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        List<Node> nodeList = new ArrayList<>();
        List<Edge> edgeList = new ArrayList<>();
        graph.edgeList = edgeList;
        graph.nodeList = nodeList;
        int nodes = 1000;
        int sqSize = (int) Math.sqrt(nodes);
        Map<Point, Node> positionNodeMap = new HashMap<>();
        // Nodes
        for (int i = 0; i < sqSize; i++) {
            for (int j = 0; j < sqSize; j++) {
                Node node = new Node();
                node.stampedLock = new StampedLock();
                node.point = new Point(i, j);
                node.color = Color.GRAY;
                node.outgoingEdges = new ArrayList<>();
                positionNodeMap.put(node.point, node);
            }
        }
        // Horizontal Edges
        for (int i = 0; i < sqSize; i++) {
            for (int j = 0; j < sqSize - 1; j++) {
                Node node1 = positionNodeMap.get(new Point(i, j));
                Node node2 = positionNodeMap.get(new Point(i, j + 1));
                long stamp1 = node1.stampedLock.writeLock();
                long stamp2 = node2.stampedLock.writeLock();
                try {
                    node1.outgoingEdges.add(new Edge(node1, node2));
                } finally {
                    node1.stampedLock.unlockWrite(stamp1);
                    node2.stampedLock.unlockWrite(stamp2);
                }
            }
        }

        // Vertical edges
        for (int i = 0; i < sqSize - 1; i++) {
            for (int j = 0; j < sqSize; j++) {
                Node node1 = positionNodeMap.get(new Point(i, j));
                Node node2 = positionNodeMap.get(new Point(i + 1, j));
                long stamp1 = node1.stampedLock.writeLock();
                long stamp2 = node2.stampedLock.writeLock();
                try {
                    node1.outgoingEdges.add(new Edge(node1, node2));
                } finally {
                    node1.stampedLock.unlockWrite(stamp1);
                    node2.stampedLock.unlockWrite(stamp2);
                }
            }
        }

        // Assume that
        exportToGraphviz(graph);
    }


}
