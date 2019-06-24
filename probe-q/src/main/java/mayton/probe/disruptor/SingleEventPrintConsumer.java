package mayton.probe.disruptor;

import com.lmax.disruptor.EventHandler;

public class SingleEventPrintConsumer {

    public EventHandler<ValueEvent>[] getEventHandler() {
        EventHandler<ValueEvent> eventHandler
                = (event, sequence, endOfBatch)
                -> print(event.getValue(), sequence);
        return new EventHandler[] { eventHandler };
    }

    private void print(int id, long sequenceId) {
        System.out.println("Id is " + id + " sequence id that was used is " + sequenceId);
    }

}
