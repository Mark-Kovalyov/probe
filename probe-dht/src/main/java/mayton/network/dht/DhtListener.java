package mayton.network.dht;

import bt.bencoding.BEEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import the8472.bencode.BDecoder;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.arraycopy;
import static mayton.network.dht.Utils.binhex;

public class DhtListener implements Runnable, DhtListenerMBean {

    private Logger logger;

    public AtomicBoolean stopped = new AtomicBoolean(false);

    private AtomicInteger packetsReceived = new AtomicInteger(0);
    private AtomicInteger packetsRejected = new AtomicInteger(0);

    public int port;
    public String threadName;

    private BEEncoder beEncoder = new BEEncoder();
    private BDecoder decoder = new BDecoder();

    public Stats stats = new Stats();

    public static DhtListener createDhtListenerWithMBean(String threadName, int port) throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("mayton.network.dht:type=DhtListener_" + threadName + "_" + port);
        DhtListener dhtListener = new DhtListener(threadName, port);
        mbs.registerMBean(dhtListener, name);
        return dhtListener;
    }

    public DhtListener(String threadName, int port) {
        this.port = port;
        this.threadName = threadName + "/" + port;
        logger = LogManager.getLogger("DhtListener." + threadName);
    }

    public void stop() {
        stopped.set(true);
    }

    @Override
    public void run() {
        logger.info("Started {} listener", threadName);

        DatagramSocket socket;
        try {
            socket = new DatagramSocket(port);
            socket.setReuseAddress(true);
            byte[] buf = new byte[320]; // WTF?

            while (!stopped.get()) {

                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);

                decodeCommand(packet);
            }
            socket.close();
        } catch (IOException e) {
            logger.error(e);
        }
        stopped.set(true);
    }

    void decodeCommand(DatagramPacket packet) {
        stats.packetsReceived++;
        packetsReceived.incrementAndGet();
        byte[] packetData = packet.getData();
        try{
            Map<String, Object> res = decoder.decode(ByteBuffer.wrap(packetData));
            logger.info("{} :: Received DHT UDP packet : from {}:{} ({}) with data:  {}\n",
                    threadName,
                    packet.getAddress(),
                    GeoUtils.decodeCountryCity(packet.getAddress().getHostAddress()),
                    packet.getPort(),
                    binhex(packetData, true));

            logger.trace("Decoded with structure : {}", Utils.dumpDEncodedMap(res, 0));
        } catch (Exception ex) {
            int offset = 16 * 3 + 12;
            logger.warn("Unable to parse datagram: {}, trying to appy offset {}", binhex(packetData), offset);

            byte[] packetDataCropped = new byte[packetData.length - offset];
            arraycopy(packetData, offset, packetDataCropped, 0, packetDataCropped.length);
            try {
                Map<String, Object> res = decoder.decode(ByteBuffer.wrap(packetDataCropped));
                String countryCity = GeoUtils.decodeCountryCity(packet.getAddress().getHostAddress());
                logger.info("{} :: Received DHT UDP packet : from {}:{} ({}) with offset = {} data:  {}\n",
                        threadName,
                        packet.getAddress(),
                        countryCity,
                        packet.getPort(),
                        offset,
                        binhex(packetDataCropped, true));
                logger.trace("Decoded with structure : {}", Utils.dumpDEncodedMap(res, 0));
            } catch (Exception ex2) {
                logger.error("Unable to parse datagram: {}, with length = {}", binhex(packetDataCropped), packetDataCropped.length);
                stats.packetsRejected++;
                packetsRejected.incrementAndGet();
            }
        }
    }

    @Override
    public int getPacketsReceived() {
        return packetsReceived.get();
    }

    @Override
    public int getPacketsParsed() {
        return packetsReceived.get() - packetsRejected.get();
    }

    @Override
    public int getPacketsRejected() {
        return packetsRejected.get();
    }
}
