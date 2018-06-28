package mayton.probes;

import org.jgrapht.Graph;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.ComponentNameProvider;
import org.jgrapht.io.GraphExporter;
import org.jgrapht.traverse.DepthFirstIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.Iterator;

public class AppJGraphT {
    
    static final Logger logger = LoggerFactory.getLogger(AppJGraphT.class);

    public static void main(String[] args) throws MalformedURLException, ExportException, org.jgrapht.io.ExportException {
        Graph<String, DefaultEdge> stringGraph = createStringGraph();

        // note undirected edges are printed as: {<v1>,<v2>}
        logger.info("-- toString output");
        //@example:toString:begin
        logger.info(stringGraph.toString());
        //@example:toString:end


        //@example:traverse:begin

        // create a graph based on URL objects
        Graph<URL, DefaultEdge> hrefGraph = createHrefGraph();

        // find the vertex corresponding to www.jgrapht.org
        //@example:findVertex:begin
        URL start = hrefGraph.vertexSet().stream().filter(
                url -> url.getHost().equals("www.jgrapht.org")).findAny().get();
        //@example:findVertex:end

        //@example:traverse:end

        // perform a graph traversal starting from that vertex
        logger.info("-- traverseHrefGraph output");
        traverseHrefGraph(hrefGraph, start);
        logger.info("");

        logger.info("-- renderHrefGraph output");
        renderHrefGraph(hrefGraph);
        logger.info("");
    }

    private static Graph<URL, DefaultEdge> createHrefGraph() throws MalformedURLException {
        //@example:urlCreate:begin
        Graph<URL, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

        URL google = new URL("http://www.google.com");
        URL wikipedia = new URL("http://www.wikipedia.org");
        URL jgrapht = new URL("http://www.jgrapht.org");

        // add the vertices
        g.addVertex(google);
        g.addVertex(wikipedia);
        g.addVertex(jgrapht);

        // add edges to create linking structure
        g.addEdge(jgrapht, wikipedia);
        g.addEdge(google, jgrapht);
        g.addEdge(google, wikipedia);
        g.addEdge(wikipedia, google);
        //@example:urlCreate:end

        return g;
    }

    private static void traverseHrefGraph(Graph<URL, DefaultEdge> hrefGraph, URL start) {
        //@example:traverse:begin
        Iterator<URL> iterator = new DepthFirstIterator<>(hrefGraph, start);
        while (iterator.hasNext()) {
            URL url = iterator.next();
            logger.info(url.toString());
        }
        //@example:traverse:end
    }

    /**
     * Render a graph in DOT format.
     *
     * @param hrefGraph a graph based on URL objects
     */
    private static void renderHrefGraph(Graph<URL, DefaultEdge> hrefGraph) throws ExportException, org.jgrapht.io.ExportException {
        //@example:render:begin
        // use helper classes to define how vertices should be rendered,
        // adhering to the DOT language restrictions
        ComponentNameProvider<URL> vertexIdProvider = (url) -> url.getHost().replace('.', '_');

        ComponentNameProvider<URL> vertexLabelProvider = (url) -> url.toString();

        GraphExporter<URL, DefaultEdge> exporter = new org.jgrapht.io.DOTExporter<>(
                vertexIdProvider,
                vertexLabelProvider,
                null);

        Writer writer = new StringWriter();
        exporter.exportGraph(hrefGraph, writer);
        logger.info(writer.toString());
        //@example:render:end
    }

    /**
     * Create a toy graph based on String objects.
     *
     * @return a graph based on String objects.
     */
    private static Graph<String, DefaultEdge> createStringGraph() {

        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        return g;
    }

}
