package mayton.probe.docker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinioDockerRunner {

    static Logger logger = LogManager.getLogger(MinioDockerRunner.class);

    public static void main(String[] args) throws Exception {

        logger.info("Starting docker");

        DockerRunner dockerRunner = new DockerRunner(new DockerParams("minio/minio")
                .withRestartOption(DockerParams.DockerRestart.NO)
                    .addEnvironment("MINIO_ACCESS_KEY", "AKIAIOSFODNN7EXAMPLE")
                    .addEnvironment("MINIO_SECRET_KEY", "****************************************")
                    .addVolumeMapping("/db/TR",        "/data")
                    .addVolumeMapping("/db/TR/.minio", "/root/.minio")
                    .addPortMapping(9000, 9000)
                .addCommandLineArgument("server")
                .addCommandLineArgument("/data"));

        Thread dockerThread = new Thread(dockerRunner);

        dockerThread.start();

        logger.info("Waiting for 15 minutes");

        Thread.sleep(15 * 60 * 1000L);

        logger.info("Stopping docker");

        dockerThread.interrupt();

        logger.info("Stopped");



    }
}
