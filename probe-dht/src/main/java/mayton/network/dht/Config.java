package mayton.network.dht;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("sensitive.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long emuleEd2kId = Long.parseLong(properties.getProperty("emule.ed2k.id","0"));

    public static String emuleKadClientId = properties.getProperty("emule.kad.clientId","");
}
