package mayton.probe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinioDockerRunner {

    static Logger logger = LogManager.getLogger(MinioDockerRunner.class);

    public static void main(String[] args) throws Exception {

        String userDir = System.getProperty("user.dir");

        logger.info("Starting docker");

        DockerRunner dockerRunner = new DockerRunner(new DockerParams("minio/minio")
                .withRestartOption(DockerParams.DockerRestart.NO)
                    .addEnvironment("MINIO_ACCESS_KEY", "**************************")
                    .addEnvironment("MINIO_SECRET_KEY", "@@@@@@@@@@@@@@@@@@@@@")
                    .addVolumeMapping("/db/TR", "/data")
                    .addVolumeMapping(userDir + "/docker", "/root/.minio")
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
