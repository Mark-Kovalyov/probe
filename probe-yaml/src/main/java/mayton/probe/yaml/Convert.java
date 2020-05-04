package mayton.probe.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

public class Convert {

    public static void main(String[] args) throws IOException {
        Yaml yaml = new Yaml();
        LinkedHashMap<String, Object> res = yaml.load(new FileInputStream("src/test/resources/04-mike.yaml"));
        FileWriter writer = new FileWriter("build/04-mike-converted.yaml");
        yaml.dump(res, writer);
    }

}
