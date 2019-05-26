package mayton.probe.ignite;

import org.apache.ignite.Ignition;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {

        ClientConfiguration configuration = new ClientConfiguration().setAddresses("127.0.0.1:10800");

        IgniteClient igniteClient = Ignition.startClient(configuration);


        igniteClient.close();

    }
}
