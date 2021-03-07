package mayton.probe.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("name", "Mike");
        data.put("age", 23);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        data.put("birthday", simpleDateFormat.parse("1954-01-02 16:44:00"));
        data.put("hobbies", new String[] { "movies" , "bike" , "hacking" , "singing" });
        data.put("movies", new Object[] {"Star Wars", "Dark Knigth"});

        data.put("friends", new Object[] {
            new LinkedHashMap() {{
                put("name", "Jessica");
                put("age", 22);
            }},
            new LinkedHashMap() {{
                put("name", "Bob");
                put("age", 26);
            }}
        });

        Yaml yaml = new Yaml();
        FileWriter writer = new FileWriter("build/test.yaml");
        yaml.dump(data, writer);

    }

}
