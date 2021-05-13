package org.example;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.common.math.Quantiles;
import org.apache.commons.cli.*;

import java.util.*;

public class CountWithGrouping {


    public static String subPrefix(String prefix, String key) {
        String sub = key.substring(prefix.length() + 1);
        int index = sub.indexOf("/");
        return sub.substring(0, index);
    }

    public static void printHistogram(List<Integer> histogram, int multiplier) {
        System.out.println("Less than (k), Count");
        for (int i = 0; i < histogram.size(); i++) {
            System.out.printf("%d,%d\n", i * 10, histogram.get(i));
        }
    }

    public static void printGrouping() {

    }

    public static Options getOptions() {
        final Options options = new Options();
        options.addOption(new Option("a", "accesskey", true, "Access key (optional, gathered from OS env)"));
        options.addOption(new Option("s", "securedkey", true, "Secured key (optional, gathered from OS env)"));
        options.addOption(new Option("e", "endpoint", true, "Aws endpoint (default == s3.us-east-1.amazonaws.com)"));
        options.addOption(new Option("b", "bucket", true, "Secured key"));
        options.addOption(new Option("r", "region", true, "Region (default : us-east-1"));
        options.addOption(new Option("p", "prefix", true, "Prefix"));
        options.addOption(new Option("h", "histogram", false, "Analyze length histogram"));
        return options;
    }

    public static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("myapp", "Usage", getOptions(), "", true);
    }

    public static void main(String[] args) throws ParseException {

        if (args.length == 0) {
            printHelp();
            return;
        }

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(getOptions(), args);
        System.out.printf("argList = %s\n", cmd.getArgList());


        String accessKey       = cmd.hasOption("a") ? cmd.getOptionValue("a") : System.getenv("AWS_ACCESS_KEY_ID");
        String securedKey      = cmd.hasOption("s") ? cmd.getOptionValue("s") : System.getenv("AWS_SECRET_KEY");
        String bucket          = cmd.getOptionValue('b');
        String prefix          = cmd.getOptionValue("p");
        String serviceEndpoint = cmd.hasOption("e") ? cmd.getOptionValue("e") : "s3.us-east-1.amazonaws.com";
        String region          = cmd.hasOption("r") ? cmd.getOptionValue("r") : "us-east-1";
        boolean analyzeHistogram = cmd.hasOption("h");

        System.out.printf("bucket = %s, prefix = %s\n", bucket, prefix);

        // Copy paste from product code
        ClientConfiguration clientConfiguration = new ClientConfiguration()
                .withMaxConnections(100)
                .withSocketTimeout(120_000)
                .withConnectionTTL(20_000)
                .withConnectionTimeout(10_000);

        AmazonS3 amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(accessKey, securedKey)))
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(serviceEndpoint, region)
                )
                .withClientConfiguration(clientConfiguration)
                .build();

        ListObjectsV2Request req = new ListObjectsV2Request()
                .withBucketName(bucket)
                .withPrefix(prefix)
                .withMaxKeys(1000);

        ListObjectsV2Result result;

        int MULTIPLIER = 10;

        List<Integer> histogramWithStep10 = new ArrayList<>(0);
        List<Integer> raw = new ArrayList<>();

        Map<String, Integer> subPrefixMap = new HashMap<>();


        long cnt = 0;
        long maxSize = 0;
        long size = 0;

        do {
            result = amazonS3.listObjectsV2(req);
            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                size = objectSummary.getSize();
                // Provide disk persitence
                raw.add((int)size);
                String key = objectSummary.getKey();
                //System.out.printf(" - %s (size: %d)\n", objectSummary.getKey(), objectSummary.getSize());
                cnt++;
                if (analyzeHistogram) {
                    maxSize = Math.max(maxSize, size);
                    int index = (int) size / MULTIPLIER;
                    while (index >= histogramWithStep10.size()) {
                        histogramWithStep10.add(0);
                    }
                    histogramWithStep10.set(index, histogramWithStep10.get(index) + 1);
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
            System.out.println("Next Continuation Token: " + token);
            req.setContinuationToken(token);

        } while (result.isTruncated());

        System.out.println("Count: " + cnt);
        System.out.println("Max size: " + maxSize);
        System.out.println("");

        subPrefixMap.entrySet().stream().forEach(item -> {
            System.out.println(prefix + "/" + item.getKey() + " : " + item.getValue());
        });

        if (analyzeHistogram) {
            printHistogram(histogramWithStep10, MULTIPLIER);
        }

        System.out.printf("Max              : %14.2f\n", (double)maxSize);
        System.out.printf("Median           : %14.2f\n", Quantiles.median().compute(raw));
        System.out.printf("97-th Percentile : %14.2f\n", Quantiles.percentiles().index(97).compute(raw));
    }


}
