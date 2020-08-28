package mayton.compression.margins;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MarginProcessor {

    Map<Integer, Integer> freqLeftMargins = new LinkedHashMap<>();
    Map<Integer, Integer> freqRightMargins = new LinkedHashMap<>();

    static Logger logger = LoggerFactory.getLogger(MarginProcessor.class);

    private static int detectLeftMargin(String line) {
        int i = 0;
        while(i < line.length() && line.charAt(i) == ' ') {
            i++;
        }
        return i;
    }

    private static int detectRightMargin(String line) {
        return line.length();
    }

    public void dumpStats() throws IOException {
        logger.trace("Left margins:");

        Yaml yaml = new Yaml();
        LinkedHashMap<String, Object> report = new LinkedHashMap<>();
        LinkedHashMap<String, Object> leftLinkedHashMap = new LinkedHashMap<>();
        freqLeftMargins.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey)).forEach(item -> {
            logger.trace("{} - {}", item.getKey(), item.getValue());
            leftLinkedHashMap.put("" + item.getKey(), item.getValue());
        });
        report.put("left", leftLinkedHashMap);

        logger.trace("Right margins:");
        LinkedHashMap<String, Object> rightLinkedHashMap = new LinkedHashMap<>();
        freqRightMargins.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey)).forEach(item -> {
            logger.trace("{} - {}", item.getKey(), item.getValue());
            rightLinkedHashMap.put("" + item.getKey(), item.getValue());
        });

        report.put("right", rightLinkedHashMap);
        yaml.dump(report, new FileWriter("war-and-society-3.margins.yaml"));
    }

    public void processMargins() throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("text/war-and-society-3.txt", StandardCharsets.UTF_8))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                int leftMargin = detectLeftMargin(line);
                if (leftMargin > 0) {
                    freqLeftMargins.putIfAbsent(leftMargin, 1);
                    freqLeftMargins.computeIfPresent(leftMargin, (key, oldValue) -> oldValue + 1);
                }
                int rightMargin = detectRightMargin(line);
                if (rightMargin > 0) {
                    freqRightMargins.putIfAbsent(leftMargin, 1);
                    freqRightMargins.computeIfPresent(rightMargin, (key, oldValue) -> oldValue + 1);
                }
            }
        }
        dumpStats();
    }


}
