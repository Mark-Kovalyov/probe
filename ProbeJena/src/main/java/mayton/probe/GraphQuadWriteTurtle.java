package mayton.probe;

import org.apache.jena.graph.Graph;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.mem.GraphMem;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.ModelCom;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.sparql.util.NodeFactoryExtra;

import java.util.ArrayList;
import java.util.List;

import static org.apache.jena.graph.NodeFactory.*;

public class GraphQuadWriteTurtle {

    public static void main(String[] args) {

        Graph graph = new GraphMem();


        Node graphName = createLiteral("Graph-1");

        List<Triple> list = new ArrayList<>();

        list.add(new Triple(
                createURI(":Krimhilda"),
                createURI("a"),
                createBlankNode()));

        list.add(new Triple(
                createURI(":Siegfrid"),
                createURI("rdf:type"),
                createLiteral("foaf:Person")));

        list.add(new Triple(
                createURI(":Siegfrid"),
                createURI("foaf:age"),
                NodeFactoryExtra.intToNode(30)));

        list.add(new Triple(
                createURI(":Siegfrid"),
                createURI("armor:SwordLength"),
                NodeFactoryExtra.doubleToNode(13.4)));


        list.add(new Triple(
                createURI(":Siegfrid"),
                createURI("time:cureentTime"),
                NodeFactoryExtra.nowAsDateTime()));


        list.add(new Triple(
                createBlankNode(),
                createURI("a"),
                createLiteral("Blank Value")));

        Quad quad = new Quad(graphName, list.get(0));

        list.forEach(graph::add);

        RDFDataMgr.write(System.out, graph, Lang.TTL);

        Model model = ModelFactory.createModelForGraph(graph);



    }

}
