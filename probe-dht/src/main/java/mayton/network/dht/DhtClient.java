package mayton.network.dht;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import static mayton.network.dht.ConsoleUtils.println;
import static mayton.network.dht.DhtListener.createDhtListenerWithMBean;

/**
 *  Torrent client
 *  <pre>
 *      Transmisssion :: Received DHT UDP packet : from /*:49306 with data:
 *     64 31 3A 61 64 32 3A 69 64 32 30 3A 86 6D 34 86 05  : d1:ad2:id20: m4
 *     16 E5 60 6D 3E 91 37 9A DF 74 77 59 62 E9 E0 39 3A  :   `m> 7  twYb  9:
 *     69 6E 66 6F 5F 68 61 73 68 32 30 3A A5 76 6D BC D9  : info_hash20: vm
 *     C3 F0 E4 6E 5F 85 16 7B 1E 9E E8 08 51 71 37 36 3A  :    n_  {    Qq76:
 *     6E 6F 73 65 65 64 69 31 65 65 31 3A 71 39 3A 67 65  : noseedi1ee1:q9:ge
 *     74 5F 70 65 65 72 73 31 3A 74 34 3A 38 2B 00 00 31  : t_peers1:t4:8+  1
 *     3A 76 34 3A 55 54 B2 3C 31 3A 79 31 3A 71           : :v4:UT <1:y1:q
 *
 *
 *     $ tcpdump -i wlp7s0 udp port 4665 or port 51413 -vv -X
 *     $ tcpdump -i wlp7s0 udp portrange 1000-65536 -vv -X *
 *     $ tcpdump -i wlp7s0 udp port '(4665 or 4672 or 51413 or 16680 or 49001)' -vv -X *
 *
 *     $ tshark -i wlp7s0 -f "udp port 51413"
 *     $ tshark -i wlp7s0 -R "bittorrent" -any_other_options
 *     $ tcpdump -Xs 0 -ni wlp7s0 `cpp -P tcpdump-gre-utp.cpp`
 *
 *     ed2k://|file|eMule0.42f-Sources.zip|2407949|CC8C3B104AD58678F69858F1F9B736E9|/
 *
 *     </pre>
 */
public class DhtClient {



    private static Logger logger = LogManager.getLogger(DhtClient.class);

    static DhtListener amule_port_4665;
    static DhtListener amule_port_4672;
    static DhtListener torrent_port_51413;
    static DhtListener vuze_port_16680;
    static DhtListener vuze_port_49001;
    static DhtListener torrent_port_46434;

    static Thread amule_port_4665_thread;
    static Thread amule_port_4672_thread;
    static Thread torrent_port_51413_thread;
    static Thread torrent_port_46434_thread;
    static Thread vuze_port_16680_thread;
    static Thread vuze_port_49001_thread;

    private static void stopAll() throws InterruptedException {
        //amule_port_4665.stop();
        //amule_port_4672.stop();
        //torrent_port_51413.stop();
        //vuze_port_16680.stop();
        //vuze_port_49001.stop();

        //amule_port_4665_thread.join();
        //amule_port_4672_thread.join();
        //torrent_port_51413_thread.join();

        Stats result = new Stats();

        println(amule_port_4665.threadName + ": " + amule_port_4665.stats);
        println(amule_port_4672.threadName + ": " + amule_port_4672.stats);
        println(torrent_port_51413.threadName + ": " + torrent_port_51413.stats);

        result.sumStats(amule_port_4665.stats);
        result.sumStats(amule_port_4672.stats);
        result.sumStats(torrent_port_51413.stats);

        println("Overall: " + result);
    }

    public static void main(String[] args) throws IOException, InterruptedException, NotCompliantMBeanException, InstanceAlreadyExistsException, MalformedObjectNameException, MBeanRegistrationException {

        String id = Config.emuleKadClientId;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Receive shutdown hook signal!");
            try {
                stopAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error(e);
            }
        }));

        amule_port_4665    = createDhtListenerWithMBean("A-Mule", 4665);
        amule_port_4672    = createDhtListenerWithMBean("A-Mule", 4672);
        torrent_port_51413 = createDhtListenerWithMBean("Transm", 51413); // *
        torrent_port_46434 = createDhtListenerWithMBean("Transm", 46434);
        vuze_port_16680    = createDhtListenerWithMBean("Vuze", 16680); // Used for the 'LAN peer finder' functionality.
        vuze_port_49001    = createDhtListenerWithMBean("Vuze", 49001); // Used for Mainline DHT (if that plugin is installed)

        amule_port_4665_thread = new Thread(amule_port_4665);
        amule_port_4665_thread.start();

        amule_port_4672_thread = new Thread(amule_port_4672);
        amule_port_4672_thread.start();

        torrent_port_51413_thread = new Thread(torrent_port_51413);
        torrent_port_51413_thread.start();

        torrent_port_46434_thread = new Thread(torrent_port_46434);
        torrent_port_46434_thread.start();

        vuze_port_16680_thread = new Thread(vuze_port_16680);
        vuze_port_16680_thread.start();

        vuze_port_49001_thread = new Thread(vuze_port_49001);
        vuze_port_49001_thread.start();

        Thread waiter = new Thread(() -> {
            try { amule_port_4665_thread.join(); } catch (InterruptedException ex) {}
            try { amule_port_4672_thread.join(); } catch (InterruptedException ex) {}
            try { torrent_port_51413_thread.join(); } catch (InterruptedException ex) {}
            try { vuze_port_16680_thread.join(); } catch (InterruptedException ex) {}
            try { vuze_port_49001_thread.join(); } catch (InterruptedException ex) {}
        });

        waiter.start();
        waiter.join();

    }




}
