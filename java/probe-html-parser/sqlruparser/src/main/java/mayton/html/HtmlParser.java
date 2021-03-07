package mayton.html;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ApplicationContext;
import java.io.IOException;

import static java.lang.System.getProperty;

@SpringBootApplication
public class HtmlParser {

    static Logger logger = LogManager.getLogger(HtmlParser.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.setProperty("log4j.configurationFile","log4j2.xml");
        System.out.printf("LogManager.context = %s%n", LogManager.getContext(true));
        logger.info(":: start with user.dir = {}", getProperty("user.dir"));
        SpringApplication springApplication = new SpringApplication(HtmlParser.class);
        springApplication.addListeners(new ApplicationPidFileWriter("./html-parser.pid"));
        ApplicationContext ctx = springApplication.run(args);

        String implementation = "vipwalker";
        // TODO: Implement with Spring SPEL configuration
        WalkerService walkerService = ctx.getBean(implementation, WalkerService.class);
        walkerService.walk();
    }

}
