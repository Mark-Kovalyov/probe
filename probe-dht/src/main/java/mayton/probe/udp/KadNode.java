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
