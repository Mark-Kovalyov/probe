package mayton.semanticweb.rdfhandlers;

import mayton.lib.SofarTracker;
import mayton.semanticweb.FieldDescriptor;
import mayton.semanticweb.Trackable;
import mayton.semanticweb.Utils;
import mayton.semanticweb.jfr.FlushDmlNameEvent;
import mayton.semanticweb.jfr.FlushDmlValueEvent;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TRDatabaseSQLWriterHandler extends TRTableProcess implements RDFHandler, Trackable, AutoCloseable {

    static Logger logger = LoggerFactory.getLogger(TRDatabaseSQLWriterHandler.class);

    private PrintWriter pw;

    private Resource prevSubject = null;

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
    public void startRDF() {
        pw.println("begin transaction;");
    }

    @Override
    public void handleNamespace(String prefix, String uri) {
        // No action needed
    }

    private Map<String, String> node = null;

    private void newNode(Resource subject, IRI predicate, Value object) {
        if (node == null) {
            node = new LinkedHashMap<>();
        } else {
            flushNodeToSql();
        }
        node.put("id", subject.toString());
        node.put(predicate.toString(), object.toString());
    }

    private void upgradeCurrentNode(IRI predicate, Value object) {
        node.put(predicate.toString(), object.toString());
    }

    private void flushNodeToSql() {
        if (!node.isEmpty()) {
            pw.print("INSERT INTO ");
            pw.print(tableName);
            pw.print("(");

            FlushDmlNameEvent flushDmlNameEvent = new FlushDmlNameEvent();
            flushDmlNameEvent.begin();

            pw.print(node.keySet()
                    .stream()
                    .map(Utils::filterFieldName)
                    .map(Utils::filterSQLWordsAndDashStyle)
                    .collect(Collectors.joining(",")));

            flushDmlNameEvent.commit();

            pw.print(") VALUES (");

            FlushDmlValueEvent flushDmlValueEvent = new FlushDmlValueEvent();
            flushDmlValueEvent.begin();

            pw.print(node.values()
                    .stream()
                    .map(Utils::filterFieldValue)
                    .collect(Collectors.joining(",")));

            flushDmlValueEvent.commit();

            pw.print(");");
            pw.println();
            node.clear();
        }
    }


    @Override
    public void handleStatement(Statement st) {
        Resource subject = st.getSubject();
        IRI predicate = st.getPredicate();
        Value object = st.getObject();

        if (logger.isTraceEnabled()) {
            logger.trace("{}, {}, {}", subject, predicate, object);
        }

        if (prevSubject == null) {
            // new Node
            newNode(subject, predicate, object);
        } else {
            if (prevSubject.equals(subject)) {
                upgradeCurrentNode(predicate, object);
            } else {
                newNode(subject, predicate, object);
            }
        }

        prevSubject = subject;

        cnt++;
        if (cnt % 100 == 0) {
            synchronized (sofarTracker) {
                sofarTracker.update(cnt);
            }
        }

    }

    @Override
    public void endRDF() {
        flushNodeToSql();
        sofarTracker.update(sofarTracker.getSize());
        MDC.remove("mode");
        pw.println("commit;");
    }

    @Override
    public void handleComment(String comment) {
        // No action
    }

    @Override
    public void close() throws Exception {
        pw.close();
    }
}
