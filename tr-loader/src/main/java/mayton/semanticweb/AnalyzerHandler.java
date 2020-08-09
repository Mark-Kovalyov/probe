package mayton.semanticweb;

import mayton.lib.SofarTracker;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static mayton.semanticweb.Constants.TABLE_NAME;

public class AnalyzerHandler implements RDFHandler, Trackable, AutoCloseable {

    public SofarTracker sofarTracker;

    private long cnt;

    static Logger logger = LoggerFactory.getLogger(AnalyzerHandler.class);

    //          key       filteredName    xsd:dataType
    private Map<IRI, Pair<String,         String>> predicates = new LinkedHashMap<>(24);

    private PrintWriter printWriter;

    public AnalyzerHandler(String tableName) throws IOException {
        printWriter = new PrintWriter(new FileWriter("sql/ddl/" + tableName + ".sql"));
        cnt = 0;
    }

    @Override
    public void startRDF() throws RDFHandlerException {
        logger.info(":: start RDF");
    }

    @Override
    public void endRDF() throws RDFHandlerException {
        sofarTracker.update(sofarTracker.getSize());
        logger.info("create table {}(", TABLE_NAME);
        printWriter.println("create table (" + TABLE_NAME);
        logger.info("     id varchar(255) primary key,");
        printWriter.println("     id varchar(255) primary key,");
        predicates.entrySet().stream().forEach(entry -> {
            String fieldName = entry.getValue().getKey();
            /*String res = String.format("%s / %s $ %s",
                    entry.getKey().toString(),
                    entry.getValue().getKey(),
                    entry.getValue().getValue());*/

            logger.info("    {} text, ", fieldName);
            printWriter.printf("    %s text,\n", fieldName);
        });
        logger.info(");");
        printWriter.println(");");
    }

    @Override
    public void handleNamespace(String prefix, String uri) throws RDFHandlerException {
        logger.info(":: handle namespace prefix = {} , uri {}", prefix, uri);
    }

    @Override
    public void handleStatement(Statement st) throws RDFHandlerException {
        Resource context = st.getContext();
        if (context!=null) {
            logger.trace(":: context = {}", context.toString());
        }
        Resource subject = st.getSubject();
        logger.trace(":: subject = {}", subject.toString());

        IRI predicate    = st.getPredicate();
        logger.trace(":: predicate = {}", predicate.toString());

        Value object     = st.getObject();
        logger.trace(object.stringValue());

        String fieldName = Utils.formatFieldName(predicate.getLocalName());

        // TODO: Detect type
        predicates.put(predicate, Pair.of(fieldName, ""));
        cnt++;
        sofarTracker.update(cnt);
    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {

    }

    public Map<IRI, Pair<String, String>> getPredicates() {
        return Collections.unmodifiableMap(predicates);
    }

    @Override
    public void bind(SofarTracker sofarTracker) {
        this.sofarTracker = sofarTracker;
    }

    @Override
    public void close() throws Exception {
        printWriter.close();
    }
}
