package mayton.probe;

import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Log4jUtils {

    public static void init(){
        Properties p = new Properties();
        p.setProperty("log4j.rootLogger",             "INFO, mayton");
        p.setProperty("log4j.appender.mayton",        "org.apache.log4j.ConsoleAppender");
        p.setProperty("log4j.appender.mayton.layout", "org.apache.log4j.PatternLayout");
        p.setProperty("log4j.appender.mayton.layout.ConversionPattern", "%d{yyyy-MM-dd HH:mm:ss} [%-5p] %c{1}:%L - %m%n");
        PropertyConfigurator.configure(p);
    }

    public static void initFromFile(String file) throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream(file));
        PropertyConfigurator.configure(p);
    }

    public void x(){
        FileInputStream s;
    }

}
