package mayton.semanticweb.rdfhandlers;

import mayton.lib.SofarTracker;
import mayton.semanticweb.FieldDescriptor;
import mayton.semanticweb.Trackable;
import mayton.semanticweb.Utils;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static mayton.semanticweb.Utils.filter;


public class TRDatabaseSQLWriterHandler extends TRTableProcess implements RDFHandler, Trackable, AutoCloseable {

    static Logger logger = LoggerFactory.getLogger(TRDatabaseSQLWriterHandler.class);

    private PrintWriter pw;

    private Resource subject = null;

    private Map<IRI, String> currentDmlOperatorFields = new LinkedHashMap<>(24);

    public TRDatabaseSQLWriterHandler(Map<IRI, FieldDescriptor> fieldDescriptorMap, PrintWriter pw, String tableName) {
        super(tableName);
        MDC.put("mode", "SQL writer");
        this.fieldDescriptorMap = fieldDescriptorMap;
        this.pw = pw;
        this.cnt = 0;
    }

    @Override
    public void bind(SofarTracker sofarTracker) {
        this.sofarTracker = sofarTracker;
    }

    @Override
    public void startRDF() throws RDFHandlerException {
        pw.println("begin transaction;");
    }

    @Override
    public void handleNamespace(String prefix, String uri) throws RDFHandlerException {

    }

    public void processInsert(Statement st) {
        pw.print("INSERT INTO ");
        pw.print(tableName);
        pw.print("(ID, ");
        pw.print(currentDmlOperatorFields.keySet()
                .stream()
                .map(IRI::getLocalName)
                .map(Utils::formatFieldName)
                .collect(Collectors.joining(",")));

        pw.print(") VALUES ('");

        if (st == null) {
            pw.print(filter(subject.stringValue()));
        } else {
            pw.print(filter(st.getSubject().stringValue()));
        }
        pw.print("',");



        pw.print(currentDmlOperatorFields.values()
                .stream()
                .map(x -> filter(x))
                /*.map(Utils::trimQuotes)                     // "12345" => 12345
                .map(Utils::filterNamespaces)               // http://permid.org/123/ => 123/
                .map(Utils::filterXmlSchemaTypes)           // "2004-11-18T00:00:00Z"^^<http://www.w3.org/2001/XMLSchema#dateTime> => "2004-11-18T00:00:00Z"
                .map(Utils::trimSlash)                      // 12345/ => 12345
                .map(Utils::wrapPostgresLiteral)            // слон => U&'\0441\043B\043E\043D'*/
                .collect(Collectors.joining(",")));

        currentDmlOperatorFields.clear();

        pw.print(");");
        pw.println();
    }

    @Override
    public void handleStatement(Statement st) throws RDFHandlerException {
        if (logger.isTraceEnabled()) {
            logger.trace("{}, {}, {}", st.getSubject(), st.getPredicate(), st.getObject());
        }
        cnt++;
        if (cnt % 10 == 0) {
            synchronized (sofarTracker) {
                sofarTracker.update(cnt);
            }
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
    public void endRDF() throws RDFHandlerException {
        sofarTracker.update(sofarTracker.getSize());
        MDC.remove("mode");
        pw.println("commit;");
        pw.printf("# overall = %d", cnt);
    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {

    }


    @Override
    public void close() throws Exception {
        pw.close();
    }
}
