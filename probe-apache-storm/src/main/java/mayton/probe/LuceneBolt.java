package mayton.probe;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.*;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

// TODO
public class LuceneBolt implements IBasicBolt {
    @Override
    public void prepare(Map stormConf, TopologyContext context) {

    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {

    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
