package mayton;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static Options createOptions() {
        return new Options()
                .addRequiredOption("i", "inputFolder", true, "InputFolder");
    }

    public static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar jparser-1.0.jar", createOptions(), true);
    }

    public static void main(String[] args) throws ParseException, IOException {
        CommandLineParser parser = new DefaultParser();
        Options options = createOptions();
        if (args.length == 0) {
            printHelp();
        } else {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("h")) {
                printHelp();
                return;
            }
            process(line);
        }
    }

    private static void process(CommandLine line) throws IOException {
        logger.info("Start");
        JavaClassFileVisitor jcfv = new JavaClassFileVisitor();
        Files.walkFileTree(Path.of(line.getOptionValue("inputFolder")), jcfv);
        //Files.walkFileTree(Path.of("/storage/git.java"), jcfv);
        logger.info("Finish");
    }
}
