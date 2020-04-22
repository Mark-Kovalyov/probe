package mayton.network.dht;

import bt.bencoding.BEEncoder;
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

public class KadListener implements Runnable {

    private static Logger logger = LogManager.getLogger(KadListener.class);

    AtomicBoolean stopped = new AtomicBoolean(false);

    private int port;
    private String name;

    public KadListener(String name, int port) {
        this.port = port;
        this.name = name;
    }

    public void stop() {
        stopped.set(true);
    }

    @Override
    public void run() {
        logger.info("Started {} listener", name);

        DatagramSocket socket = null;
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

                //socket.send(packet);
                decodeCommand(packet);
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void decodeCommand(DatagramPacket packet) {
        byte[] arr = packet.getData();
        BEEncoder beEncoder = new BEEncoder();
        BDecoder decoder = new BDecoder();

        //00000000 : 64 31 3A 61 64 32 3A 69 64 32 30 3A 92 29 EA 69 53  : d1:ad2:id20: ) iS
        //00000010 : 73 9D A8 D4 C7 DB 54 F9 DE D4 72 9C 4A 9E 5D 39 3A  : s     T   r J ]9:
        //00000020 : 69 6E 66 6F 5F 68 61 73 68 32 30 3A 92 29 EA 7F 14  : info_hash20: ) 
        //00000030 : 4B E6 7D A7 D5 39 3E F2 12 3A E1 EC C4 2B F0 65 31  : K }  9>  :   + e1
        //00000040 : 3A 71 39 3A 67 65 74 5F 70 65 65 72 73 31 3A 74 32  : :q9:get_peers1:t2
        //00000050 : 3A EE AB 31 3A 76 34 3A 4C 54 01 00 31 3A 79 31 3A  : :  1:v4:LT  1:y1:
        //00000060 : 71                                                  : q
        //
        //
        //2020-04-21 10:54:54 [INFO ] 17  : mayton.network.dht.KadListener Decoded :
        // {a={id=[-110, 41, -22, 105, 83, 115, -99, -88, -44, -57, -37, 84, -7, -34, -44, 114, -100, 74, -98, 93],
        // info_hash=[-110, 41, -22, 127, 20, 75, -26, 125, -89, -43, 57, 62, -14, 18, 58, -31, -20, -60, 43, -16]},
        // q=[103, 101, 116, 95, 112, 101, 101, 114, 115],
        // t=[-18, -85],
        // v=[76, 84, 1, 0],
        // y=[113]}

        //if (arr[0] == 0x64) {
        try{
            Map<String, Object> res = decoder.decode(ByteBuffer.wrap(packet.getData()));

            logger.info("{} :: Received DHT UDP packet : from {}:{} ({}) with data:  {}\n",
                    name,
                    packet.getAddress(),
                    GeoUtils.decodeCountryCity(packet.getAddress().getHostAddress()),
                    packet.getPort(),
                    binhex(packet.getData(), true));

            logger.info("Decoded : {}", res);

            logger.info("Decoded with BDecoder: {}", Utils.dumpDEncodedMap(res, 0));

        } catch (Exception ex) {
            logger.info("{} :: Received unparsable UDP packet : from {}:{} ({}) with data:  {}\n",
                    name,
                    packet.getAddress(),
                    GeoUtils.decodeCountryCity(packet.getAddress().getHostAddress()),
                    packet.getPort(),
                    binhex(packet.getData(), true));
        }
    }
}
