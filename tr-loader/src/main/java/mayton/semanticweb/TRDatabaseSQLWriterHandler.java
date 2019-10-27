package mayton.semanticweb;

import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.eclipse.rdf4j.rio.RDFHandlerException;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TRDatabaseSQLWriterHandler implements RDFHandler {

    private Map<IRI, Pair<String, String>> predicates;

    private PrintWriter pw;

    private Resource subject = null;

    private Map<IRI, String> currentDmlOperatorFields = new LinkedHashMap<>(24);

    public TRDatabaseSQLWriterHandler(Map<IRI, Pair<String, String>> predicates, PrintWriter pw) {
        this.pw = pw;
        this.predicates = predicates;
    }

    @Override
    public void startRDF() throws RDFHandlerException {

    }

    @Override
    public void endRDF() throws RDFHandlerException {
        processInsert(null);
        pw.println("commit;");
        pw.close();
    }

    @Override
    public void handleNamespace(String prefix, String uri) throws RDFHandlerException {

    }

    public void processInsert(Statement st) {
        pw.print("INSERT INTO ");
        pw.print(Constants.TABLE_NAME);
        pw.print("(id, ");
        pw.print(currentDmlOperatorFields.keySet().stream().map(IRI::getLocalName).map(name -> Utils.formatFieldName(name)).collect(Collectors.joining(",")));
        pw.print(") VALUES (");
        if (st == null) {
            pw.print(subject);
        } else {
            pw.print(st.getSubject().toString());
        }
        pw.print(currentDmlOperatorFields.values().stream().map(value -> "'" + Utils.wrapPostgresLiteral(value) + "'").collect(Collectors.joining(",")));
        pw.print(");");
        pw.println();
    }

    @Override
    public void handleStatement(Statement st) throws RDFHandlerException {
        if (subject == null) {
            subject = st.getSubject();
        } else if (subject.equals(st.getSubject())) {
            currentDmlOperatorFields.put(st.getPredicate(), st.getObject().toString());
        } else {
            processInsert(st);
            currentDmlOperatorFields.clear();
            currentDmlOperatorFields.put(st.getPredicate(), st.getObject().toString());
            subject = st.getSubject();
        }

    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {

    }
}
