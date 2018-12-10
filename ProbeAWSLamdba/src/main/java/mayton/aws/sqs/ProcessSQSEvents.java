package mayton.aws.sqs;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

//import com.amazonaws.services.lambda.runtime.events.SQSEvent;
//import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;

public class ProcessSQSEvents implements RequestHandler<Void, Void>{

    private static final String arn = "arn:aws:sqs:eu-central-1:023683237670:ProbeQueue1";

    @Override
    public Void handleRequest(Void input, Context context) {
        return null;
    }
}
