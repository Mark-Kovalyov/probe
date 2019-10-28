package mayton.semanticweb;

import mayton.lib.SofarTracker;
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

import static mayton.semanticweb.Utils.*;

public class TRDatabaseSQLWriterHandler implements RDFHandler, Trackable {

    private Map<IRI, Pair<String, String>> predicates;

    private PrintWriter pw;

    private Resource subject = null;

    private Map<IRI, String> currentDmlOperatorFields = new LinkedHashMap<>(24);

    private SofarTracker sofarTracker;

    private long cnt = 0;

    public TRDatabaseSQLWriterHandler(Map<IRI, Pair<String, String>> predicates, PrintWriter pw) {
        this.pw = pw;
        this.predicates = predicates;
        this.cnt = 0;
    }

    @Override
    public void startRDF() throws RDFHandlerException {

    }

    @Override
    public void endRDF() throws RDFHandlerException {
        sofarTracker.update(sofarTracker.getSize());
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
        pw.print("(ID, ");
        pw.print(currentDmlOperatorFields.keySet()
                .stream()
                .map(IRI::getLocalName)
                .map(name -> formatFieldName(name))
                .collect(Collectors.joining(",")));

        pw.print(") VALUES ('");

        if (st == null) {
            pw.print(filterNamespaces(subject.stringValue()));
        } else {
            pw.print(filterNamespaces(st.getSubject().stringValue()));
        }
        pw.print("',");

        pw.print(currentDmlOperatorFields.values()
                .stream()
                //.map(value -> trimQuotes(value))          // "12345" => 12345
                .map(Utils::filterNamespaces)               // http://permid.org/123/ => 123/
                .map(Utils::filterDateTime)                 // "2004-11-18T00:00:00Z"^^<http://www.w3.org/2001/XMLSchema#dateTime> => "2004-11-18T00:00:00Z"
                .map(value -> value.endsWith("/") ? value.substring(0, value.length() - 1) : value) // 12345/ => 12345
                .map(Utils::wrapPostgresLiteral)            // Hello \t world =>
                .map(value -> "'" + value + "'")
                .collect(Collectors.joining(",")));

        pw.print(");");
        pw.println();
    }

    @Override
    public void handleStatement(Statement st) throws RDFHandlerException {
        cnt++;
        synchronized (sofarTracker) {
            sofarTracker.update(cnt);
        }
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

    @Override
    public void bind(SofarTracker sofarTracker) {
        this.sofarTracker = sofarTracker;
    }
}
