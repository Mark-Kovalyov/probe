package mayton.probe.eclipse.rdf;

/////////////////////////////////////////
import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;

///////////////////////////////////////////
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.SimpleIRI;
import org.eclipse.rdf4j.model.impl.SimpleLiteral;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class StreamStatementHandler implements RDFHandler, StreamStatementHandlerMBean {

    static Logger logger = LoggerFactory.getLogger("StreamStatementHandler");

    private Dataset dataset;

    public Map<String,Integer> subjMap = new HashMap<>();
    public Map<String,Integer> predMap = new HashMap<>();
    public Map<String,Integer> objMap  = new HashMap<>();

    @Override
    public void startRDF() throws RDFHandlerException {
        logger.info(":: startRDF ::");
    }

    @Override
    public void endRDF() throws RDFHandlerException {
        logger.info(":: endRDF ::");
    }

    @Override
    public void handleNamespace(String prefix, String uri) throws RDFHandlerException {
        logger.info(":: handleNamespace {} {} ::", prefix, uri);
    }

    @Override
    public void handleStatement(Statement st) throws RDFHandlerException {

        Resource subjRes = st.getSubject();
        Resource predRes = st.getPredicate();
        Value    objVal  = st.getObject();

        String subj = subjRes.toString();
        String pred = predRes.toString();
        String obj = objVal.stringValue();

        // TODO: Refactor with Syncrhonized map abilities
        synchronized (predMap) {
            predMap.put(pred, 1);
        }

        Model model = dataset.getDefaultModel();

        if (subjRes instanceof SimpleIRI &&
                predRes instanceof SimpleIRI) {
            if (objVal instanceof SimpleIRI) {
                model.add(
                    model.getResource(subj),
                    model.getProperty(pred),
                    model.getResource(obj));
            } else if (objVal instanceof SimpleLiteral) {
                model.add(
                        model.getResource(subj),
                        model.getProperty(pred),
                        obj);
            } else {
                logger.warn("Unknown triple : {} {} {}",
                        subjRes.getClass(),
                        predRes.getClass(),
                        objVal.getClass());
            }

        } else {
            logger.warn("Unknown triple : {} {} {}",
                    subjRes.getClass(),
                    predRes.getClass(),
                    objVal.getClass());
        }



    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {
        logger.info(comment);
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }
}
