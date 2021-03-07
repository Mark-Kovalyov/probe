package mayton.probe.eclipse.rdf;

import mayton.probe.eclipse.rdf.jmx.StreamStatementHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ReportUtils {

    static Logger logger = LoggerFactory.getLogger(StreamStatementHandler.class);

    public static void printMap(String header,Map<String, Integer> map) {
        logger.info(header);
        logger.info("=============================================================");
        for(Map.Entry<String,Integer> entry : map.entrySet()) {
            logger.info("{} : {}", entry.getKey(), entry.getValue());
        }
    }

}
