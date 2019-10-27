package mayton.semanticweb;

import com.google.common.base.CaseFormat;
import mayton.lib.SofarTracker;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class TRDDLCreator implements RDFHandler {

    public SofarTracker sofarTracker;

    private long cnt;

    static Logger logger = LoggerFactory.getLogger(TRDDLCreator.class);

    private Map<IRI, String> predicates = new LinkedHashMap<>();

    public TRDDLCreator(SofarTracker sofarTracker) {
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
        logger.info("create table rt_org(");
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
        //logger.info(":: hanlde statement {}", st.toString());
        //Resource context = st.getContext();
        IRI predicate = st.getPredicate();
        //Resource subject = st.getSubject();
        predicates.put(predicate, StringUtils.replace(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, predicate.getLocalName()), "-", "_"));
        cnt++;
        synchronized (sofarTracker) {
            sofarTracker.update(cnt);
        }
    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {

    }

    public Map<IRI, String> getPredicates() {
        return Collections.unmodifiableMap(predicates);
    }
}
