package mayton.compression.simplify;

import mayton.compression.GraphAlgorithm;
import mayton.compression.graphs.Edge;
import mayton.compression.graphs.Graph;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.util.Properties;

public class SimplifyGraph implements GraphAlgorithm {

    static XLogger xLogger = XLoggerFactory.getXLogger(SimplifyGraph.class);

    static Logger logger = LoggerFactory.getLogger(SimplifyGraph.class);


    @Override
    public @NotNull Graph process(@NotNull Graph graph, @NotNull Properties parameters) {
        xLogger.entry(parameters);
        for(Edge edge : graph.getEdgeWeigthMap().keySet()) {

        }
        return null;
    }
}
