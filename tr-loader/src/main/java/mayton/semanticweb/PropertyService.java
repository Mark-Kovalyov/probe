package mayton.semanticweb;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class PropertyService {

    private static PropertyService inst;

    private static CommandLine commandLine;

    public static PropertyService createInstance() {
        if (inst == null) {
            inst = new PropertyService();
        }
        return inst;
    }

    static Properties properties = new Properties();

    private PropertyService() {
        try {
            properties.load(new FileInputStream("tr-loader.properties"));
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
