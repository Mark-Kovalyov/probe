package mayton.semanticweb.rdfhandlers;

import org.apache.commons.csv.CSVPrinter;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.eclipse.rdf4j.rio.RDFHandlerException;

public class TRDatabaseStatementCache extends TRTableProcess implements RDFHandler {

    CSVPrinter csvPrinter;

    public TRDatabaseStatementCache(String tableName) {
        super(tableName);
    }

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

    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {

    }
}
