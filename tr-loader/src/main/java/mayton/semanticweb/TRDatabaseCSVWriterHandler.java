package mayton.semanticweb;

import mayton.lib.SofarTracker;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

// TODO: Implement
public class TRDatabaseCSVWriterHandler implements RDFHandler, Trackable {

    static Logger logger = LoggerFactory.getLogger(TRDatabaseCSVWriterHandler.class);

    private PrintWriter pw;

    private Resource subject = null;

    private Map<IRI, String> currentDmlOperatorFields = new LinkedHashMap<>(24);

    private SofarTracker sofarTracker;

    private long cnt = 0;
    private long insertCnt = 0;

    @Override
    public void bind(SofarTracker sofarTracker) {
        this.sofarTracker = sofarTracker;
    }

    @Override
    public void startRDF() throws RDFHandlerException {
        logger.info("start RDF");
    }

    @Override
    public void endRDF() throws RDFHandlerException {
        logger.info("end RDF");
    }

    @Override
    public void handleNamespace(String prefix, String uri) throws RDFHandlerException {

    }

    @Override
    public void handleStatement(Statement st) throws RDFHandlerException {
        cnt++;
        sofarTracker.update(cnt);
        if (subject == null) {
            subject = st.getSubject();
        } else if (subject.equals(st.getSubject())) {
            currentDmlOperatorFields.put(st.getPredicate(), st.getObject().toString());
        } else {
            processCSVRow(st);
            currentDmlOperatorFields.clear();
            currentDmlOperatorFields.put(st.getPredicate(), st.getObject().toString());
            subject = st.getSubject();
        }
    }

    private void processCSVRow(Statement st) {

    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {

    }
}
