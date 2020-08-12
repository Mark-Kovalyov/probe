package mayton.semanticweb.rdfhandlers;

import mayton.lib.SofarTracker;
import mayton.semanticweb.FieldDescriptor;
import mayton.semanticweb.Trackable;
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

import static mayton.semanticweb.Utils.*;

// INSERT INTO public."Item" ("Id", name)
// VALUES  ('1', 'name1'),
//         ('2', 'name2'),
//         ('3', 'name3')
@Deprecated
public class TRDatabaseMultilineSQLWriterHandler extends TRTableProcess implements RDFHandler, Trackable {

    static Logger logger = LoggerFactory.getLogger(TRDatabaseMultilineSQLWriterHandler.class);

    private PrintWriter pw;

    private Resource subject = null;

    private Map<IRI, String> currentDmlOperatorFields = new LinkedHashMap<>(24);

    private SofarTracker sofarTracker;

    private int batchSize = 100;

    private long cnt = 0;
    private long insertCnt = 0;

    private String tableName;

    public TRDatabaseMultilineSQLWriterHandler(Map<IRI, FieldDescriptor> fieldDescriptorMap, PrintWriter pw, String tableName, int batchSize) {
        super(tableName);
        this.pw = pw;
        this.fieldDescriptorMap = fieldDescriptorMap;
        this.cnt = 0;
        this.tableName = tableName;
    }

    @Override
    public void bind(SofarTracker sofarTracker) {
        this.sofarTracker = sofarTracker;
    }

    private void printInsert() {
        pw.println();
        pw.printf("INSERT INTO %s (ID", tableName);
        /*for(Map.Entry<IRI, Pair<String,String>> entry : predicates.entrySet()) {
            pw.print(",");
            pw.print(entry.getKey());
        }*/
        /*for(IRI key : predicates.keySet()) {
            pw.print(",");
            pw.print(predicates.get(key).getKey());
        }*/
        pw.print(") VALUES");
        pw.println();
        insertCnt = 0;
    }

    @Override
    public void startRDF() throws RDFHandlerException {
        pw.printf("TRUNCATE TABLE %s;\n", tableName);
        printInsert();
    }

    @Override
    public void endRDF() throws RDFHandlerException {
        sofarTracker.update(sofarTracker.getSize());
        processInsert(null);
        pw.print(";");
        pw.close();
    }

    @Override
    public void handleNamespace(String prefix, String uri) throws RDFHandlerException {

    }

    public void processInsert(Statement st) {
        if (insertCnt > 0) {
            pw.print(",\n");
        }
        pw.print("(");
        if (st == null) {
            logger.trace("[1] statement == null");
            String pk = "'" + filterNamespaces(subject.stringValue()) + "'";
            pw.print(pk);
        } else {
            logger.trace("[2] statement == {}", st.toString());
            String pk = "'" + filterNamespaces(st.getSubject().stringValue()) + "'";
            pw.print(pk);
        }
        /*for(IRI key : predicates.keySet()) {
            pw.print(",");
            if (currentDmlOperatorFields.containsKey(key)) {
                String value = currentDmlOperatorFields.get(key);
                String resValue = wrapPostgresLiteral( // Hello =>
                            trimSlash(
                                    filterDateTime(
                                            filterNamespaces(value))));
                logger.trace("[3] {} => {}",value, resValue);
                pw.print(resValue);
            } else {
                pw.print("null");
            }
        }*/
        pw.print(")");
        insertCnt++;
        if (insertCnt > batchSize) {
            pw.print(";\n");
            printInsert();
        }
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
            processInsert(st);
            currentDmlOperatorFields.clear();
            currentDmlOperatorFields.put(st.getPredicate(), st.getObject().toString());
            subject = st.getSubject();
        }

    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {
        logger.info(":: handle comment = {}", comment);
    }


}
