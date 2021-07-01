package mayton;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {

    public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static Options createOptions() {
        return new Options()
                .addRequiredOption("s", "source", true, "Source file")
                .addOption("d", "destination", true, "Destination file");
    }

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        CommandLineParser parser = new DefaultParser();
        Options options = createOptions();
        if (args.length == 0) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar ApplicationName-1.0.jar", createOptions(), true);
        } else {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("java -jar ApplicationName-1.0.jar", createOptions(), true);
                return;
            }
            process(line);
        }
    }

    private static void process(CommandLine line) throws FileNotFoundException {
        logger.info("Start");
        Kryo kryo = new Kryo();
        kryo.register(Payment.class);

        Payment object = new Payment();
        object.setDestCardId("0000-1111");

        Output output = new Output(new FileOutputStream("file.bin"));
        kryo.writeObject(output, object);
        output.close();

        Input input = new Input(new FileInputStream("file.bin"));
        Payment object2 = kryo.readObject(input, Payment.class);
        input.close();
        logger.info("Finish");
    }
}
