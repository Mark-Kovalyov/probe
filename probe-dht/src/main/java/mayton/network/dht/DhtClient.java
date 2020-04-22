package mayton.network.dht;

import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.commons.codec.net.QuotedPrintableCodec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.*;
import java.util.Collections;

import static java.net.SocketOptions.SO_REUSEPORT;

public class DhtClient {


    // Torrent client
    // tcp        0      0 0.0.0.0:9091            0.0.0.0:*               LISTEN      8852/transmission-g
    // tcp        0      0 0.0.0.0:51413           0.0.0.0:*               LISTEN      8852/transmission-g
    // tcp6       0      0 :::51413                :::*                    LISTEN      8852/transmission-g
    // udp        0      0 0.0.0.0:51413           0.0.0.0:*                           8852/transmission-g
    //
    // KAD client
    // tcp        0      0 0.0.0.0:4662            0.0.0.0:*               LISTEN      13284/amule
    // udp        0      0 0.0.0.0:4665            0.0.0.0:*                           13284/amule
    // udp        0      0 0.0.0.0:4672            0.0.0.0:*                           13284/amule

    // Transmisssion :: Received DHT UDP packet : from /*:49306 with data:
    //64 31 3A 61 64 32 3A 69 64 32 30 3A 86 6D 34 86 05  : d1:ad2:id20: m4
    //16 E5 60 6D 3E 91 37 9A DF 74 77 59 62 E9 E0 39 3A  :   `m> 7  twYb  9:
    //69 6E 66 6F 5F 68 61 73 68 32 30 3A A5 76 6D BC D9  : info_hash20: vm
    //C3 F0 E4 6E 5F 85 16 7B 1E 9E E8 08 51 71 37 36 3A  :    n_  {    Qq76:
    //6E 6F 73 65 65 64 69 31 65 65 31 3A 71 39 3A 67 65  : noseedi1ee1:q9:ge
    //74 5F 70 65 65 72 73 31 3A 74 34 3A 38 2B 00 00 31  : t_peers1:t4:8+  1
    //3A 76 34 3A 55 54 B2 3C 31 3A 79 31 3A 71           : :v4:UT <1:y1:q

    private static Logger logger = LogManager.getLogger(DhtClient.class);

    static KadListener amule_port_4665;
    static KadListener amule_port_4672;
    static KadListener torrent_port_51413;

    private static void stopAll() {
        amule_port_4665.stop();
        amule_port_4672.stop();
        torrent_port_51413.stop();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                logger.info("Receive shutdown hook signal!");
                stopAll();
            }
        });

        amule_port_4665 = new KadListener("A-Mule#1", 4665);
        amule_port_4672 = new KadListener("A-Mule#2", 4672);
        torrent_port_51413 = new KadListener("Transmisssion", 51413);

        Thread thead1 = new Thread(amule_port_4665);
        Thread thead2 = new Thread(amule_port_4672);
        Thread thead3 = new Thread(torrent_port_51413);

        thead1.start();
        thead2.start();
        thead3.start();

        Thread waiter = new Thread(() -> {
            try { thead1.join(); } catch (InterruptedException ex) {}
            try { thead2.join(); } catch (InterruptedException ex) {}
            try { thead3.join(); } catch (InterruptedException ex) {}
        });

        waiter.start();
        waiter.join();

    }




}
