package mayton.probe.udp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class KadNode {

    public static final int K = 20;
    public static final int HASHSIZE = 160;

    static Logger logger = LoggerFactory.getLogger(KadNode.class);

    /**
     * This RPC involves one node sending a PING message to another, which presumably replies with a PONG.
     * @param ipAddress
     * @return
     */
    public boolean ping(InetAddress ipAddress, int port) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket("PING".getBytes(), 4, ipAddress, port);
        clientSocket.send(packet);

        byte[] buf = new byte[4];
        DatagramPacket receivePacket = new DatagramPacket(buf, 4);
        clientSocket.receive(receivePacket);
        if (true) {
            return true;
        }
        clientSocket.close();
        return false;
    }

    public void store(String key, String value) {

    }

    public void find_node(String key160bit) {

    }

    public void find_value(String key160bit) {

    }

    // Port(s) 	Protocol 	Service 	 Details 	Source
    // =======================================================================
    // 1900     udp         aMule
    // 52263    udp         aMule
    // 4665     udp         aMule        eDonkey2000  eDonkey2000 Server Messaging Default Port,
    //                                   Container Client Message Service, AudioReQuest
    // 4672     udp         aMule        eMule - often used (unofficial)
    //                                   Extended eMule protocol, Queue Rating, File Reask Ping,
    //                                   Kad. Kad will be 'firewalled' if NAT (Network Address Translation)
    //                                   remaps this port number.
    // 4662     tcp         aMule        Client-to-client transfers.
    //
    // 3246 	udp 	    kademlia 	 Kademlia P2P (mlnet)
    //
    // 4661 TCP (outgoing):              Port on which a server listens for connection (defined by server).
    //
    //
    // 5351 	tcp,udp 		         NAT Port Mapping Protocol -
    //                                   client-requested configuration
    //                                   for inbound connections through
    //                                   network address translators (official)
    //

    // 23705
    // 51413 	tcp,udp 	p2p 	     Commonly used by Transmission BitTorrent Client.
    //
    //


    //        4711 TCP: WebServer listening port.
    //        4712 TCP: External Connection port. Used to communicate between aMule and other applications such as aMule WebServer or aMuleCMD.
    //
    //
    // $ tcpdump -i enp7s0 udp port 4665 or port 51413 -vv -X
    // $ tcpdump -i enp7s0 udp portrange 1000-65536 -vv -X
    // $ tshark -i enp7s0 -f "udp port 51413"
    // $ tshark -i enp7s0 -R "bittorrent" -any_other_options
    // $ tcpdump -Xs 0 -ni enp7s0 `cpp -P tcpdump-gre-utp.cpp`
    // ed2k://|file|eMule0.42f-Sources.zip|2407949|CC8C3B104AD58678F69858F1F9B736E9|/
    //
    public static void main(String[] args) throws Exception {

        int DEFAULT_PORT = 4672;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, DEFAULT_PORT);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close();

    }

}
