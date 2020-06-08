package mayton.html.impl;

import com.google.inject.Singleton;
import mayton.html.Config;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;

@Singleton
public class ConfigImpl implements Config {

    private LinkedHashMap<String, Object> root;

    public ConfigImpl() {
        Yaml yaml = new Yaml();
        try {
            root = yaml.load(new FileReader("config.yaml"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to load property config.yaml from user.dir = " + System.getProperty("user.dir"));
        }
    }

    public LinkedHashMap<String, Object> getRoot() {
        return root;
    }
}
