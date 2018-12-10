package mayton.aws.probes;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class SimpleEntityHandler implements RequestHandler<Entity, Entity> {

    static Logger logger = LogManager.getLogger(SimpleStringHandler.class);

    @Override
    public Entity handleRequest(Entity input, Context context) {

        Entity entity = new Entity();
        entity.setName(UUID.randomUUID().toString());
        entity.setAge(new Random().nextInt(100));

        logger.warn("AwsRequestId = {}", context.getAwsRequestId());
        logger.warn("FunctionName = {}", context.getFunctionName());
        logger.warn("Version = {}", context.getFunctionVersion());
        logger.warn("RemainingTime(ms) = {}", context.getRemainingTimeInMillis());
        logger.warn("MemoryLimit(M)  = {}", context.getMemoryLimitInMB());
        logger.warn("Log stream name = {}", context.getLogStreamName());
        logger.warn("Log group name = {}", context.getLogGroupName());

        ClientContext clientContext = context.getClientContext();

        if (clientContext!=null) {

            if (clientContext.getEnvironment()!=null) {
                Map<String, String> env = clientContext.getEnvironment();
                for (Map.Entry<String, String> envEntry : env.entrySet()) {
                    logger.warn(":: envEntry[{}] = '{}'", envEntry.getKey(), envEntry.getValue());
                }
            }

            if (clientContext.getCustom()!=null) {
                Map<String, String> custom = clientContext.getCustom();
                for (Map.Entry<String, String> customEntry : custom.entrySet()) {
                    logger.warn(":: custom[{}] = '{}'", customEntry.getKey(), customEntry.getValue());
                }
            }

        }

        return entity;
    }
}
