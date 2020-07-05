package mayton.parsers.rutorparse;

import mayton.parsers.rutorparse.interfaces.rutor.IRutorParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ApplicationContext;

import static java.lang.System.getProperty;

@SpringBootApplication
public class RutorparseApplication {

    static Logger logger = LogManager.getLogger(RutorparseApplication.class);

	public static void main(String[] args) {
        System.setProperty("log4j.configurationFile","log4j2.xml");
        System.out.printf("LogManager.context = %s%n", LogManager.getContext(true));
        logger.info(":: start with user.dir = {}", getProperty("user.dir"));
        SpringApplication springApplication = new SpringApplication(RutorparseApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter("./rutor-parser.pid"));
        ApplicationContext ctx = springApplication.run(args);
        IRutorParser rutorParser = ctx.getBean(IRutorParser.class);
        rutorParser.parse();
	}

}
