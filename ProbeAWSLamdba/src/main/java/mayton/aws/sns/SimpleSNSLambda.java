package mayton.aws.sns;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * https://sdk.amazonaws.com/java/api/latest/
 */
public class SimpleSNSLambda implements RequestStreamHandler {

    static final String queueArn = "arn:aws:sqs:eu-central-1:xxxxxxx:Queue1";

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

        // Processing input.....

        ClientConfiguration clientConfig = new ClientConfiguration();

        // TODO
        AwsClientBuilder.EndpointConfiguration endpointConfig = null;
        // TODO
        AWSCredentialsProvider creds = new AWSStaticCredentialsProvider(null);

        // TODO
        AmazonSQS amazonSQS = AmazonSQSClient.builder()
                .withRegion(Regions.US_EAST_1)
                .withClientConfiguration(clientConfig)
                .withEndpointConfiguration(endpointConfig)
                .withCredentials(creds).build();

        amazonSQS.sendMessage("https://sqs.eu-central-1.amazonaws.com/xxxxxxx/Queue1","Message");

        amazonSQS.sendMessage(new SendMessageRequest()
                .withQueueUrl("https://sqs.eu-central-1.amazonaws.com/xxxxxxx/Queue1")
                .withMessageBody("Hello")
                .withDelaySeconds(30)
        );
    }

}
