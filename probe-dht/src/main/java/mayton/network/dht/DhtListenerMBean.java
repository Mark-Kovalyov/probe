package mayton.network.dht;

public interface DhtListenerMBean {

    int getPacketsReceived();

    int getPacketsParsed();

    int getPacketsRejected();

}
