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

    public static void exportToGraphviz(Graph graph, String filename) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(props.getProperty("dest.graph") + filename));
        pw.println("digraph {\n" +
                "    graph [pad=\"0.212,0.055\" bgcolor=lightgray  splines=line]\n" +
                "    node [style=filled]");

        graph.nodeList.stream().forEach(node -> {
            pw.printf("    %s [pos=\"%d,%d\"]\n", node.name, node.point.x, node.point.y);
        });

        graph.edgeList.stream().forEach(edge -> {
            pw.printf("    %s -> %s [label=\"%d/%d\"]\n", edge.from.name, edge.to.name, edge.flow, edge.capacity);
        });

        pw.println("}");
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        List<Node> nodeList = new ArrayList<>();
        List<Edge> edgeList = new ArrayList<>();
        graph.edgeList = edgeList;
        graph.nodeList = nodeList;
        int nodes = 25;
        String filename = "out-25.gv";
        int sqSize = (int) Math.sqrt(nodes);
        Map<Point, Node> positionNodeMap = new HashMap<>();
        // Nodes
        int cnt = 0;
        for (int i = 0; i < sqSize; i++) {
            for (int j = 0; j < sqSize; j++) {
                Node node = new Node("v" + cnt++);
                node.point = new Point(i, j);
                positionNodeMap.put(node.point, node);
                nodeList.add(node);
            }
        }
        Random random = new Random();
        // Horizontal Edges
        for (int i = 0; i < sqSize; i++) {
            for (int j = 0; j < sqSize - 1; j++) {
                Node node1 = positionNodeMap.get(new Point(i, j));
                Node node2 = positionNodeMap.get(new Point(i, j + 1));
                long stamp1 = node1.stampedLock.writeLock();
                long stamp2 = node2.stampedLock.writeLock();
                try {
                    int capacity = Math.abs(1 + random.nextInt(100));
                    Edge edge = new Edge(node1, node2, 0, capacity);
                    node1.outgoingEdges.add(edge);
                    edgeList.add(edge);
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
                    int capacity = Math.abs(1 + random.nextInt(100));
                    Edge edge = new Edge(node1, node2, 0, capacity);
                    node1.outgoingEdges.add(edge);
                    edgeList.add(edge);
                } finally {
                    node1.stampedLock.unlockWrite(stamp1);
                    node2.stampedLock.unlockWrite(stamp2);
                }
            }
        }

        // Assume that
        exportToGraphviz(graph, filename);
    }


}
