package mayton.network.dht;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import the8472.bencode.BDecoder;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static mayton.network.dht.Utils.binhex;

public class DhtListener implements Runnable {

    private Logger logger;

    AtomicBoolean stopped = new AtomicBoolean(false);

    public int port;
    public String threadName;

    public Stats stats = new Stats();

    public DhtListener(String threadName, int port) {
        this.port = port;
        this.threadName = threadName;
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
        byte[] packetData = packet.getData();
        //BEEncoder beEncoder = new BEEncoder();
        BDecoder decoder = new BDecoder();

        //00000000 : 64 31 3A 61 64 32 3A 69 64 32 30 3A 92 29 EA 69 53  : d1:ad2:id20: ) iS
        //00000010 : 73 9D A8 D4 C7 DB 54 F9 DE D4 72 9C 4A 9E 5D 39 3A  : s     T   r J ]9:
        //00000020 : 69 6E 66 6F 5F 68 61 73 68 32 30 3A 92 29 EA 7F 14  : info_hash20: ) 
        //00000030 : 4B E6 7D A7 D5 39 3E F2 12 3A E1 EC C4 2B F0 65 31  : K }  9>  :   + e1
        //00000040 : 3A 71 39 3A 67 65 74 5F 70 65 65 72 73 31 3A 74 32  : :q9:get_peers1:t2
        //00000050 : 3A EE AB 31 3A 76 34 3A 4C 54 01 00 31 3A 79 31 3A  : :  1:v4:LT  1:y1:
        //00000060 : 71                                                  : q
        //
        //00000000 : 41 00 CA 81 02 4A 77 6A 00 00 00 00 00 10 00 00 3A  : A    Jwj        :
        //00000010 : 03 00 00 D1 86 AE 60 B9 2C BD ED E9 01 A3 5F 36 3A  :       ` ,     _6:
        //00000020 : 74 61 72 67 65 74 32 30 3A A5 76 6C D8 E3 96 5E 5B  : target20: vl   ^[
        //00000030 : 02 B7 ED 73 77 3E B5 07 76 8D AB A1

        try{
            Map<String, Object> res = decoder.decode(ByteBuffer.wrap(packetData));
            logger.info("{} :: Received DHT UDP packet : from {}:{} ({}) with data:  {}\n",
                    threadName,
                    packet.getAddress(),
                    GeoUtils.decodeCountryCity(packet.getAddress().getHostAddress()),
                    packet.getPort(),
                    binhex(packetData, true));

            //logger.info("Decoded : {}", res);
            logger.info("Decoded with structure : {}", Utils.dumpDEncodedMap(res, 0));
        } catch (Exception ex) {
            // 2020-04-26 14:54:45 [WARN ] 17  : DhtListener.Transm/51413 Unable to parse datagram:
            // 00000000 : 41 00 7B D9 2F 04 7C E9 00 00 00 00 00 10 00 00 10  : A { / |
            // 00000010 : 4B 00 00 00
            int offset = 16 * 3 + 12;
            logger.warn("Unable to parse datagram: {}, trying to appy offset {}", binhex(packetData), offset);

            byte[] packetDataCropped = new byte[packetData.length - offset];
            System.arraycopy(packetData, offset, packetDataCropped, 0, packetDataCropped.length);
            try {
                Map<String, Object> res = decoder.decode(ByteBuffer.wrap(packetDataCropped));
                logger.info("{} :: Received DHT UDP packet : from {}:{} ({}) with offset = {} data:  {}\n",
                        threadName,
                        packet.getAddress(),
                        GeoUtils.decodeCountryCity(packet.getAddress().getHostAddress()),
                        packet.getPort(),
                        offset,
                        binhex(packetDataCropped, true));
                logger.info("Decoded with structure : {}", Utils.dumpDEncodedMap(res, 0));
            } catch (Exception ex2) {
                logger.error("Unable to parse datagram: {}, with length = {}", binhex(packetDataCropped), packetDataCropped.length);
            }

            stats.packetsRejected++;
        }
    }
}
