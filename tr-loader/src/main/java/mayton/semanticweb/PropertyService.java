package mayton.semanticweb;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class PropertyService {

    static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("tr-loader.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO:
    // 1) Command Line
    // 2) .properties
    // 3) OS env
    public Optional<String> lookupProperty(String propertyName) {
        if (properties.containsKey(propertyName)) {
            return Optional.of(properties.getProperty(propertyName));
        } else if (StringUtils.isNotBlank(System.getenv(propertyName))) {
            return Optional.of(System.getenv(propertyName));
        } else {
            return Optional.empty();
        }
    }

}
