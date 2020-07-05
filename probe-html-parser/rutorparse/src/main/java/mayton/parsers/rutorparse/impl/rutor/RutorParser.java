package mayton.parsers.rutorparse.impl.rutor;

import mayton.parsers.rutorparse.interfaces.rutor.IPageWalker;
import mayton.parsers.rutorparse.interfaces.rutor.IRutorParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RutorParser implements IRutorParser {

    static Logger logger = LogManager.getLogger(RutorParser.class);

    @Autowired
    IPageWalker pageWalker;

    @Override
    public void parse() {
        logger.info("Start");
        for(int i = 0; i < 10; i++) {
            pageWalker.walk(i);
        }
        logger.info("Finished");
    }

}
