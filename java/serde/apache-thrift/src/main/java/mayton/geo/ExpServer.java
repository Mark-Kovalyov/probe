package mayton.geo;

import mayton.geo.generated.GeoIpService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpServer {

    static Logger logger = LoggerFactory.getLogger(ExpServer.class);

    public static void main(String[] args) throws TTransportException {

        GeoIpService geoIpService = new GeoIpService();

        GeoIpService.Processor processor = new GeoIpService.Processor(null);

        TServerTransport serverSocket = new TServerSocket(8081);
        TServer server = new TSimpleServer(new TServer.Args(serverSocket));
        server.serve();
        logger.info("Server listening...");





    }

}
