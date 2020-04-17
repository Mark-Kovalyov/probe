package mayton.network.dht;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static mayton.network.dht.Utils.binhex;

public class KadListener implements Runnable {

    private int port;
    private String name;

    public KadListener(String name, int port) {
        this.port = port;
        this.name = name;
    }




    @Override
    public void run() {
        boolean running = true;
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(port);
            socket.setReuseAddress(true);
            byte[] buf = new byte[320]; // WTF?

            while (running) {

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

    // get_peers1
    // n0:e1:q13:announce_peer1:t8:  { * 1:y1:q
    void decodeCommand(DatagramPacket packet) {
        byte[] arr = packet.getData();
        if (arr[0] == 0x64) {
            //String received = new String(packet.getData(), 0, packet.getLength());
            System.out.printf("%s :: Received DHT UDP packet : from %s:%d with data:  %s\n",
                    name,
                    packet.getAddress(),
                    packet.getPort(),
                    binhex(packet.getData(), true));

        } else {
            System.out.printf("%s :: Received Unknown UDP packet : from %s:%d\n",
                    name,
                    packet.getAddress(),
                    packet.getPort());
        }
    }
}
