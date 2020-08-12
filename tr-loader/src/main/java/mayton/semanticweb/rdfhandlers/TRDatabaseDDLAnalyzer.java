package mayton.semanticweb.rdfhandlers;

import mayton.lib.SofarTracker;
import mayton.semanticweb.FieldDescriptor;
import mayton.semanticweb.Trackable;
import mayton.semanticweb.Utils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.StringJoiner;

public class TRDatabaseDDLAnalyzer extends TRTableProcess implements RDFHandler, Trackable, AutoCloseable {

    public SofarTracker sofarTracker;

    private long cnt;

    private FieldDescriptor primaryKeyDescriptor = new FieldDescriptor("id");

    static Logger logger = LoggerFactory.getLogger(TRDatabaseDDLAnalyzer.class);

    private PrintWriter printWriter;

    public TRDatabaseDDLAnalyzer(String tableName) throws IOException {
        super(tableName);
        MDC.put("mode", "DDL analyzer");
        fieldDescriptorMap = new LinkedHashMap<>(24);
        printWriter = new PrintWriter(new FileWriter("sql/ddl/" + tableName + ".sql"));
        this.tableName = tableName;
        cnt = 0;
    }

    @Override
    public void startRDF() throws RDFHandlerException {
        logger.info(":: start RDF");
    }



    @Override
    public void endRDF() throws RDFHandlerException {
        sofarTracker.update(sofarTracker.getSize());
        logger.info("create table {}(", tableName);
        printWriter.println("drop table " + tableName + ";");
        printWriter.print("create table " + tableName + "(\n");
        printWriter.print("    " + primaryKeyDescriptor.formatExpr() + ",\n    ");
        StringJoiner stringJoiner = new StringJoiner(",");

        fieldDescriptorMap.entrySet().stream().forEach(entry -> {
            stringJoiner.add(entry.getValue().formatExpr());
            logger.info("    {}", entry.getValue().formatExpr());
        });

        logger.info(");");
        printWriter.print(StringUtils.replace(stringJoiner.toString(),",",",\n    "));
        printWriter.println(");");
        MDC.remove("mode");
    }

    @Override
    public void handleNamespace(String prefix, String uri) throws RDFHandlerException {
        logger.info(":: handle namespace prefix = {} , uri {}", prefix, uri);
    }

    @Override
    public void handleStatement(Statement st) throws RDFHandlerException {
        logger.trace("::: {}, {}, {}", st.getSubject(), st.getPredicate(), st.getObject());
        Resource context = st.getContext();
        if (context != null) {
            logger.trace(":: context = {}", context.toString());
        }
        Resource subject = st.getSubject();
        logger.trace(":: subject = {}", subject.toString());

        IRI predicate    = st.getPredicate();
        logger.trace(":: predicate = {}", predicate.toString());

        Value object     = st.getObject();
        logger.trace(object.stringValue());

        String fieldName = Utils.formatFieldName(predicate.getLocalName());

        if (fieldDescriptorMap.containsKey(predicate)) {
            fieldDescriptorMap.get(predicate).updateMaxLength(object.stringValue().length());
        } else {
            fieldDescriptorMap.put(predicate, new FieldDescriptor(fieldName));
        }

        cnt++;
        sofarTracker.update(cnt);
    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {

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
