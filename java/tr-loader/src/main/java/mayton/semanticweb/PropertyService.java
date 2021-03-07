package mayton.semanticweb;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertyService {

    private static class Singleton {
        public static final PropertyService PROPERTY_SERVICE = new PropertyService();
    }

    public static PropertyService getInstance() {
        return Singleton.PROPERTY_SERVICE;
    }

    private static CommandLine commandLine;

    static Properties properties = new Properties();

    private PropertyService() {
        try {
            URL resource = getClass().getClassLoader().getResource("tr-loader.properties");
            properties.load(resource.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCommandLine(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    // TODO:
    // 1) Command Line
    // 2) .properties
    // 3) OS env
    public String lookupProperty(String propertyName) {
        if (commandLine.hasOption(propertyName)) {
            return commandLine.getOptionValue(propertyName);
        } else if (properties.containsKey(propertyName)) {
            return properties.getProperty(propertyName);
        } else if (StringUtils.isNotBlank(System.getenv(propertyName))) {
            return System.getenv(propertyName);
        } else {
            throw new RuntimeException("Unable to locate property " + propertyName);
        }
    }

}
