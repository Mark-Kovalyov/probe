package mayton.semanticweb;

import com.google.common.base.CaseFormat;
import mayton.lib.SofarTracker;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static mayton.semanticweb.Constants.TABLE_NAME;

public class AnalyzerHandler implements RDFHandler {

    public SofarTracker sofarTracker;

    private long cnt;

    static Logger logger = LoggerFactory.getLogger(AnalyzerHandler.class);

    //          key       filteredName    xsd:dataType
    private Map<IRI, Pair<String,         String>> predicates = new LinkedHashMap<>(24);

    public AnalyzerHandler(SofarTracker sofarTracker) {
        this.sofarTracker = sofarTracker;
        cnt = 0;
    }

    @Override
    public void startRDF() throws RDFHandlerException {
        logger.info(":: start RDF");
    }

    @Override
    public void endRDF() throws RDFHandlerException {
        synchronized (sofarTracker) {
            sofarTracker.update(sofarTracker.getSize());
        }
        logger.info("create table {}(", TABLE_NAME);
        logger.info("     id varchar(255) primary key,");
        predicates.entrySet().stream().forEach(item -> {
            logger.info("    {} text, ", item.getValue());
        });
        logger.info(");");
    }

    @Override
    public void handleNamespace(String prefix, String uri) throws RDFHandlerException {
        logger.info(":: hanlde namespace prefix = {} , uri {}", prefix, uri);
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
        synchronized (sofarTracker) {
            sofarTracker.update(cnt);
        }
    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {

    }

    public Map<IRI, Pair<String, String>> getPredicates() {
        return Collections.unmodifiableMap(predicates);
    }
}
