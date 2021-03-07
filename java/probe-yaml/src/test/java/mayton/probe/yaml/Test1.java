package mayton.probe.yaml;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

import static mayton.probe.yaml.Utils.printf;

public class Test1 {

    @Test
    public void test() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        yaml.load(new FileInputStream("src/test/resources/01.yaml"));
        yaml.load(new FileInputStream("src/test/resources/02-sequence.yaml"));
        yaml.load(new FileInputStream("src/test/resources/03-sequence-line.yaml"));
    }

    private String indent(int level) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<level;i++) sb.append(' ');
        return sb.toString();
    }


    void dumpLhm(LinkedHashMap<String, Object> res, int level) {
        res.forEach((key, value) -> {
            if (value instanceof LinkedHashMap) {
                printf("%skey = %s {\n", indent(level), key);
                dumpLhm((LinkedHashMap<String, Object>) value, level + 4);
                printf("}\n", indent(level), key);
            } else {
                printf("%skey = %s, value = %s (%s)\n", indent(level), key, String.valueOf(value), value==null? "null" : value.getClass());
            }
        });
    }

    @Test
    public void testMike() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        LinkedHashMap<String, Object> res = yaml.load(new FileInputStream("src/test/resources/04-mike.yaml"));
        dumpLhm(res, 0);
    }



}
