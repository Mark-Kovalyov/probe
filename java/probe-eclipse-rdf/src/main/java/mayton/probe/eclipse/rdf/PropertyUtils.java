package mayton.probe.eclipse.rdf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    public static Properties getDefaultProperties() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("sensitive.properties"));
        } catch (IOException ex) {
            props.put("inputFile", "OpenPermID-bulk-quote.ttl.gz");
            props.put("dataSetPath", "/db/tdb2/quote");
        }
        return props;
    }

}
