package mayton.network;

import com.github.rholder.retry.*;


import com.google.common.base.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class RaftNode {

    private static final int MAX_UDP_BLOCK_SIZE = 64 * 1024 * 1024;
    private final String nodeName;

    static class DatagramSocketCallable implements Callable<DatagramSocket> {

        private String host;
        private int port;

        public DatagramSocketCallable(String host, int port) {
            this.host = host;
            this.port = port;
        }

        @Override
        public DatagramSocket call() throws Exception {
            return new DatagramSocket(port, InetAddress.getByName(host));
        }
    }

    static Logger logger = LoggerFactory.getLogger(RaftNode.class);

    public RaftLogRecord raftLog;

    public RaftStateMachine raftStateMachine;

    private DatagramSocket datagramSocket;

    DatagramSocketCallable datagramSocketCallable;

    private String host;
    private int port;

    public RaftNode(String nodeName, String host, int port) throws UnknownHostException, SocketException {
        this.host = host;
        this.port = port;
        this.nodeName = nodeName;
        datagramSocketCallable = new DatagramSocketCallable(host, port);
        MDC.put("node", nodeName + "/" + host + ":" + port);
    }

    public DatagramSocket openDgramSocketWithBackOff() throws ExecutionException, RetryException {

        Retryer<DatagramSocket> retryer = RetryerBuilder.<DatagramSocket>newBuilder()
                .retryIfResult(Predicates.<DatagramSocket>isNull())
                .retryIfExceptionOfType(IOException.class)
                .withWaitStrategy(WaitStrategies.exponentialWait(2, 5, TimeUnit.MINUTES))
                .withStopStrategy(StopStrategies.stopAfterDelay(30, TimeUnit.SECONDS))
                .build();

        return retryer.call(datagramSocketCallable);

    }

    public AtomicBoolean running = new AtomicBoolean(true);

    public void processFunction() throws ExecutionException, RetryException {
        byte[] buf = new byte[MAX_UDP_BLOCK_SIZE];
        datagramSocket = openDgramSocketWithBackOff();
        while(running.get()) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                datagramSocket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());
                logger.info(":: received = {}", received);
            } catch (IOException e) {
                logger.error("",e);
            }
        }
    }

    public static void main(String[] args) {

        logger.info("Node started!");

    }

}
