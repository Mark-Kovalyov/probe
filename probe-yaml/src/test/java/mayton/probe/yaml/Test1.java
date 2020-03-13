package mayton.probe.yaml;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Test1 {

    @Test
    public void test() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        yaml.load(new FileInputStream("src\\test\\resources\\1.yaml"));
        yaml.load(new FileInputStream("src\\test\\resources\\02-sequence.yaml"));
        yaml.load(new FileInputStream("src\\test\\resources\\03-sequence-line.yaml"));
    }

}
