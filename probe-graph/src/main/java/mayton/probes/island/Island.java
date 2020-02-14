package mayton.probes.island;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

public class Island {

    //    o o
    //    |/
    //  o-o-o
    //   /|
    //  o o

    static int[][] heightMap = {
              { 0,0,0,0,0,0,0 },
              { 0,0,0,1,4,3,0 },
              { 0,0,1,5,1,5,0 },
              { 0,2,3,2,4,6,0 },
              { 0,2,6,4,2,0,0 },
              { 0,3,1,2,0,0,0 },
              { 0,0,0,0,0,0,0 }
    };

    public static void main(String[] args) {
        MutableGraph<IslandNode> graph = GraphBuilder.undirected().build();
        // Init nodes
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                int cell = heightMap[i][j];
                graph.addNode(new IslandNode(cell, Integer.MAX_VALUE));

            }
        }
        // Init edges

    }

}
