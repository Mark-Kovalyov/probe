package mayton.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Json2Yaml {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream(args[0]);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> result = objectMapper.readValue(is, LinkedHashMap.class);
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setIndent(4);
        dumperOptions.setAllowUnicode(true);
        Yaml yaml = new Yaml(dumperOptions);
        yaml.dump(result, new FileWriter(args[1]));
    }
}
