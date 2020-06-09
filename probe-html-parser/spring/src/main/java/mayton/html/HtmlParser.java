package mayton.html;

import mayton.html.impl.HttpWalkerServiceImpl;
import org.apache.commons.lang3.concurrent.CircuitBreaker;
import org.apache.commons.lang3.concurrent.EventCountCircuitBreaker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

import java.io.IOException;

import static java.lang.System.getProperty;

/**
 * Hello world!
 */
public class HtmlParser {

    static Logger logger = LogManager.getLogger(HtmlParser.class);

    public HtmlParser() {
        walkerService().walk("http://localhost:8080");
    }



    @Autowired
    public WalkerService walkerService() {
        return new HttpWalkerServiceImpl();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.setProperty("log4j.configurationFile","log4j2.xml");
        System.out.printf("LogManager.context = %s\n", LogManager.getContext(true));
        logger.info(":: start with user.dir = {}", getProperty("user.dir"));
        SpringApplication springApplication = new SpringApplication(HtmlParser.class);
        springApplication.addListeners(new ApplicationPidFileWriter("./html-parser.pid"));
        springApplication.run(args);
    }

}
