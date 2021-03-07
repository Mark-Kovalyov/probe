package mayton.lucene;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Trace {

    static Logger

    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Paths.get("/storage/literature/tolstoy/"))) {
            stream.forEach((row) -> {

            });
        } catch (IOException e) {

        }
    }

}
