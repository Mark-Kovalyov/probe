package mayton.probe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.getProperty;

public class DockerRunner implements Runnable {

    static Logger logger = LogManager.getLogger(DockerRunner.class);

    private DockerParams dockerParams;

    public DockerRunner(@Nonnull DockerParams dockerParams) {
        this.dockerParams = dockerParams;
    }

    @Override
    public void run() {
        ProcessBuilder processBuilder = null;

        String commandLine;

        if ("linux".equalsIgnoreCase(getProperty("os.name"))) {
            logger.info("Linux selected");
            commandLine = new UnixCommandLineFormatter().format(dockerParams);
            logger.info("command line : {}", commandLine);

            String[] dockerArguments = new String[]{"docker",
                    "run",

                    "-p", "9000:9000",

                    "-e", "\"MINIO_ACCESS_KEY=**************************\"",
                    "-e", "\"MINIO_SECRET_KEY=@@@@@@@@@@@@@@@@@@@@@\"",

                    "-v", "/db/TR:/data",
                    "-v", "/home/mayton/git/probe/probe-docker-minio/docker:/root/.minio",

                    "minio/minio",

                    "server",
                    "/data"};

            processBuilder = new ProcessBuilder(dockerArguments);

        } else {
            logger.info("Non-linux selected");
            // TODO: Implement for Windows

        }

        Process process;
        try {
            process = processBuilder.start();

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

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error(e);

        }

    }

}
