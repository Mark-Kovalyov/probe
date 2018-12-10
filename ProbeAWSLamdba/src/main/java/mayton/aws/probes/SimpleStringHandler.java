package mayton.aws.probes;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// mayton.aws.probes.SimpleStringHandler::handleRequest
public class SimpleStringHandler implements RequestHandler<String,String> {

    static Logger logger = LogManager.getLogger(SimpleStringHandler.class);

    @Override
    public String handleRequest(String input, Context context) {
        logger.info(":: [1] Start");

        logger.warn("AwsRequestId = {}", context.getAwsRequestId());
        logger.warn("FunctionName = {}", context.getFunctionName());
        logger.warn("Version = {}", context.getFunctionVersion());
        logger.warn("RemainingTime(ms) = {}", context.getRemainingTimeInMillis());
        logger.warn("MemoryLimit(M)  = {}", context.getMemoryLimitInMB());
        logger.warn("Log stream name = {}", context.getLogStreamName());
        logger.warn("Log group name = {}", context.getLogGroupName());


        logger.info(":: [2] End");
        return "Hello world";
    }
}
