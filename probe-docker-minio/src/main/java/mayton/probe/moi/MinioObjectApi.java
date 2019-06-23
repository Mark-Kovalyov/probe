package mayton.probe.moi;

import io.minio.MinioClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinioObjectApi {

    // docker run -p 9000:9000 -e MINIO_ACCESS_KEY=AKIAIOSFODNN7EXAMPLE -e MINIO_SECRET_KEY=****************************************
    //                         -v /db/TR:/data -v /db/TR/.minio:/root/.minio minio/minio server /data

    static Logger logger = LogManager.getLogger(MinioObjectApi.class);

    public static void main(String[] args) {
        try {
            // Create a minioClient with the MinIO Server name, Port, Access key and Secret key.
            MinioClient minioClient = new MinioClient(  "http://localhost:9000",
                                                        "AKIAIOSFODNN7EXAMPLE",
                                                        "****************************************");

            // Check if the bucket already exists.
            boolean isExist = minioClient.bucketExists("asiatrip");

            if(isExist) {
                logger.warn("Bucket already exists.");
            } else {
                // Make a new bucket called asiatrip to hold a zip file of photos.
                minioClient.makeBucket("asiatrip");
            }

            // Upload the zip file to the bucket with putObject
            minioClient.putObject("asiatrip","pic.jpg", "~/pic.jpg");

            logger.info("X successfully uploaded as Y to `asiatrip` bucket.");
        } catch(Exception e) {
            logger.error(e);
        }
    }

}
