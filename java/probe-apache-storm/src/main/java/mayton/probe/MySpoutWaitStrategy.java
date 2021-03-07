package mayton.probe;

import org.apache.storm.spout.ISpoutWaitStrategy;

import java.util.Map;

public class MySpoutWaitStrategy implements ISpoutWaitStrategy {
    @Override
    public void prepare(Map conf) {

    }

    @Override
    public void emptyEmit(long streak) {

    }
}
