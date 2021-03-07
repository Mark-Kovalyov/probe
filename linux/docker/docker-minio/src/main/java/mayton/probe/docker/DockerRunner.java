package mayton.probe.docker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.System.getProperty;

public class DockerRunner implements Runnable {

    static Logger logger = LogManager.getLogger(DockerRunner.class);

    private DockerParams dockerParams;

    public DockerRunner(@Nonnull DockerParams dockerParams) {
        this.dockerParams = dockerParams;
    }

    private static String[] listToArray(List<String> arg) {
        String[] arr = new String[arg.size()];
        for (int i = 0; i < arg.size(); i++) {
            arr[i] = arg.get(i);
        }
        return arr;
    }

    @Override
    public void run() {

        List<String> commandLineWords = new UnixCommandLineFormatter().format(dockerParams);
        logger.info("command line : {}", commandLineWords.toString());
        String[] dockerArguments = listToArray(commandLineWords);
        ProcessBuilder processBuilder = new ProcessBuilder(dockerArguments);

        try {
            Process process = processBuilder.start();

            logger.info("Process started");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                logger.info("STDOUT : {}", line);
            }

            while ((line = errorReader.readLine()) != null) {
                logger.info("STDERR : {}", line);
            }

            int exitCode = process.waitFor();

            logger.warn("Exited with error code : {}", exitCode);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }

    }

}
