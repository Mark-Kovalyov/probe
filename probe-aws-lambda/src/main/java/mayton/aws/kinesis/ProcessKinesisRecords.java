package mayton.aws.kinesis;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;

public class ProcessKinesisRecords implements RequestHandler<KinesisEvent, Void>{

    @Override
    public Void handleRequest(KinesisEvent event, Context context) {

        for(KinesisEvent.KinesisEventRecord rec : event.getRecords()) {
            System.out.println(new String(rec.getKinesis().getData().array()));
        }
        return null;
    }
}
