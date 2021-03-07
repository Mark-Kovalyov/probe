package mayton.aws.probes;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

// mayton.aws.probes.CloudWatchGenerator::handleRequest
public class CloudWatchGenerator implements RequestHandler<Entity,String> {

    @Override
    public String handleRequest(Entity input, Context context) {

        LambdaLogger logger = context.getLogger();

        logger.log(":: Watch event = " + input.toString());

        return "{ 'hello' : 'true' }";
    }

}
