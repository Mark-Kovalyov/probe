package mayton.aws;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.common.math.Quantiles;
import com.google.common.math.Stats;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class CountWithGrouping {

    static Logger logger = LoggerFactory.getLogger(CountWithGrouping.class);

    public static String subPrefix(String prefix, String key) {
        String sub = key.substring(prefix.length() + 1);
        int index = sub.indexOf("/");
        return sub.substring(0, index);
    }

    public static Options getOptions() {
        final Options options = new Options();
        options.addOption(new Option("e", "endpoint",   true, "Aws endpoint (default == s3.us-east-1.amazonaws.com)"));
        options.addOption(new Option("b", "bucket",     true, "Bucket Name"));
        options.addOption(new Option("r", "region",     true, "Region (default : eu-west-1 (Ireland)"));
        options.addOption(new Option("p", "prefix",     true, "Prefix"));
        options.addOption(new Option("h", "histogram",  false, "Analyze length histogram"));
        options.addOption(new Option("x", "analyzeextensions",  false, "Analyze extensions"));
        return options;
    }

    public static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("myapp", "Usage", getOptions(), "", true);
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            printHelp();
            return;
        }

        try {

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(getOptions(), args);

            System.out.printf("argList = %s\n", cmd.getArgList());


            //String accessKey    = cmd.hasOption("a") ? cmd.getOptionValue("a") : System.getenv("AWS_ACCESS_KEY_ID");
            //String securedKey   = cmd.hasOption("s") ? cmd.getOptionValue("s") : System.getenv("AWS_SECRET_KEY");
            //String sessionToken = cmd.hasOption("t") ? cmd.getOptionValue("t") : System.getenv("AWS_SESSION_TOKEN");

            String bucket = cmd.getOptionValue("b");
            String prefix = cmd.getOptionValue("p");

            String serviceEndpoint = cmd.hasOption("e") ? cmd.getOptionValue("e") : "s3.us-east-1.amazonaws.com";
            String region = cmd.hasOption("r") ? cmd.getOptionValue("r") : Regions.DEFAULT_REGION.getName();
            boolean analyzeHistogram = cmd.hasOption("h");

            logger.info("serviceEndpoint = {}, bucket = {}, prefix = {}, region = {}", serviceEndpoint, bucket, prefix, region);

            AmazonS3 amazonS3Client = AmazonS3ClientBuilder
                    .standard()
                    .withRegion(region)
                    .build();

            logger.info("amazonS3Client dump = {}", amazonS3Client.toString());

            ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request()
                    .withBucketName(bucket)
                    .withPrefix(prefix)
                    .withMaxKeys(1000);

            logger.info("listObjectsV2Request dump = {}", listObjectsV2Request.toString());

            ListObjectsV2Result result;

            List<Integer> raw = new ArrayList<>();

            Map<String, Integer> subPrefixMap = new HashMap<>();
            Map<String, StatsBlock> extensions = new HashMap<>();

            long cnt = 0;
            long maxSize = 0;
            long size = 0;

            long oldestTime = Long.MAX_VALUE;

            int limit = 20000;

            do {
                result = amazonS3Client.listObjectsV2(listObjectsV2Request);
                for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                    size = objectSummary.getSize();
                    // Provide disk persitence
                    raw.add((int) size);
                    String key = objectSummary.getKey();
                    logger.debug(" {} (size: {})", objectSummary.getKey(), objectSummary.getSize());

                    if (objectSummary.getLastModified().getTime() < oldestTime) {
                        oldestTime = objectSummary.getLastModified().getTime();
                    }

                    cnt++;
                    limit--;
                    if(limit == 0) {
                        break;
                    }
                    String subPrefixKey = subPrefix(prefix, key);

                    if (subPrefixMap.containsKey(subPrefixKey)) {
                        int tcnt = subPrefixMap.get(subPrefixKey);
                        subPrefixMap.put(subPrefixKey, tcnt + 1);
                    } else {
                        subPrefixMap.put(subPrefixKey, 0);
                    }

                }
                // If there are more than maxKeys keys in the bucket, get a continuation token
                // and list the next objects.
                String token = result.getNextContinuationToken();
                logger.info("Next Continuation Token: {}", token);
                listObjectsV2Request.setContinuationToken(token);

            } while (result.isTruncated());

            System.out.println("Count: " + cnt);
            System.out.println("Max size: " + maxSize);
            System.out.println("");

            subPrefixMap.entrySet().stream().forEach(item -> {
                logger.info("{}/{}:{}", prefix, item.getKey(), item.getValue());
            });

            Stats stats = Stats.of(raw);

            System.out.printf("Oldest file      : %s\n", new Date(oldestTime));
            System.out.printf("Max              : %14.2f bytes\n", stats.max());
            System.out.printf("Mean             : %14.2f bytes\n", stats.mean());
            System.out.printf("STDEV            : %14.2f bytes\n", stats.populationStandardDeviation());
            System.out.printf("Median           : %14.2f bytes\n", Quantiles.median().compute(raw));
            System.out.printf("3-th Quartille   : %14.2f bytes\n", Quantiles.percentiles().index(75).compute(raw));
            System.out.printf("95-th Percentile : %14.2f bytes\n", Quantiles.percentiles().index(95).compute(raw));
            System.out.printf("97-th Percentile : %14.2f bytes\n", Quantiles.percentiles().index(97).compute(raw));

        } catch (Exception ex) {
            logger.error("", ex);
        }
    }


}

