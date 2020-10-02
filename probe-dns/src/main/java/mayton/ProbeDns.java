package mayton;

import java.io.IOException;
import java.net.*;

public class ProbeDns {

    private static byte[] createDnsRequest(String s) {
        return new byte[0];
    }   

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        String msg = "";
        byte[] buf = createDnsRequest("sql.ru");
        address = Inet4Address.getByName("8.8.8.8");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());

    }



}
