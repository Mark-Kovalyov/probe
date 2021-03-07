package mayton.semanticweb.rdfhandlers;

import mayton.lib.SofarTracker;
import mayton.semanticweb.Trackable;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class TRDatabaseCountingHandler extends TRTableProcess implements RDFHandler, Trackable {

    public TRDatabaseCountingHandler(String tableName) {
        super(tableName);
    }

    static Logger logger = LoggerFactory.getLogger(TRDatabaseCountingHandler.class);

    @Override
    public void startRDF() {
        MDC.put("mode", "Counting");
    }

    @Override
    public void endRDF() {
        MDC.remove("mode");
    }

    @Override
    public void handleNamespace(String prefix, String uri) {
        // No action
    }

    @Override
    public void handleStatement(Statement st) {
        cnt++;
        if (logger.isTraceEnabled()) {
            logger.trace("{}, {}, {}", st.getSubject(), st.getPredicate(), st.getObject());
        }
    }

    @Override
    public void handleComment(String comment) {
        // No action
    }

    public long getStatements() {
        return cnt;
    }

    @Override
    public void bind(SofarTracker sofarTracker) {
        // No action
    }
}
