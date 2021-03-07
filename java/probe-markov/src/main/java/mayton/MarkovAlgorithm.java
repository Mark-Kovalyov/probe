package mayton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

@SuppressWarnings({"java:S106", "java:S3457"})
public class MarkovAlgorithm {

    static final class Expression<A,B> {

        private final A a;
        private final B b;

        public Expression(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public A getKey() {
            return a;
        }

        public B getValue() {
            return b;
        }
    }

    public static void main(String[] args) throws IOException {

        List<String[]> rules = readRules(args[0]);
        List<String> tests = readTests(args[1]);

        Pattern pattern = Pattern.compile("^(?<key>[^#]*?)\\s+->\\s+(\\.?)(?<replacement>.*)");

        for (int i = 0; i < tests.size(); i++) {
            String origTest = tests.get(i);

            List<String[]> captures = new ArrayList<>();
            for (String rule : rules.get(i)) {
                Matcher m = pattern.matcher(rule);
                if (m.find()) {
                    String[] groups = new String[m.groupCount()];
                    for (int j = 0; j < groups.length; j++)
                        groups[j] = m.group(j + 1);
                    captures.add(groups);
                }
            }

            String test = origTest;
            String copy = test;

            for (int j = 0; j < captures.size(); j++) {
                String[] c = captures.get(j);
                test = test.replace(c[0], c[2]);
                if (c[1].equals("."))
                    break;
                if (!test.equals(copy)) {
                    j = -1; // redo loop
                    copy = test;
                }
            }

            System.out.printf("%s\n%s\n\n", origTest, test);
        }
    }

    private static List<String> readTests(String path) throws IOException {
        return Files.readAllLines(Paths.get(path), UTF_8);
    }

    private static List<String[]> readRules(String path) throws IOException {
        String ls = System.lineSeparator();
        String lines = new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
        List<String[]> rules = new ArrayList<>();
        for (String line : lines.split(ls + ls))
            rules.add(line.split(ls));
        return rules;
    }
}