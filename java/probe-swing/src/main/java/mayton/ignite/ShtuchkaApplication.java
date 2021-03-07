package mayton.ignite;


import org.apache.ignite.Ignition;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShtuchkaApplication {

    static Logger logger = LoggerFactory.getLogger(ShtuchkaApplication.class);

    ShtuchkaController shtuchkaController;



    public ShtuchkaApplication() {
        shtuchkaController = new ShtuchkaController();
    }

    public static void main(String[] args) {
        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");
        try (IgniteClient igniteClient = Ignition.startClient(cfg)) {

            //JFrame frame = new ShtuchkaFrame(igniteClient);
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setVisible(true);

        } catch (Exception ex) {
            logger.error("Exception!", ex);
        }
    }

}
