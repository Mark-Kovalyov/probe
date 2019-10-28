package mayton.semanticweb;

import mayton.lib.SofarTracker;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.eclipse.rdf4j.rio.RDFHandlerException;

public class CountingHandler implements RDFHandler, Trackable {

    private long statements = 0;

    @Override
    public void startRDF() throws RDFHandlerException {

    }

    @Override
    public void endRDF() throws RDFHandlerException {

    }

    @Override
    public void handleNamespace(String prefix, String uri) throws RDFHandlerException {

    }

    @Override
    public void handleStatement(Statement st) throws RDFHandlerException {
        statements++;
    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {

    }

    public long getStatements() {
        return statements;
    }

    @Override
    public void bind(SofarTracker sofarTracker) {

    }
}
