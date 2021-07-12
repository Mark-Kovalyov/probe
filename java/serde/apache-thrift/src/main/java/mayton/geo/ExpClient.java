package mayton.geo;

import mayton.geo.generated.GeoIpCity;
import mayton.geo.generated.GeoIpService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.*;

import java.io.IOException;

public class ExpClient {


    public static GeoIpCity create() {
        GeoIpCity geoIpCity = new GeoIpCity();
        geoIpCity.setStartIpNum(0);
        geoIpCity.setEndIpNum(2);
        geoIpCity.setCity("Kiev");
        geoIpCity.setLatitude(0.0);
        geoIpCity.setLongitude(2.0);
        return geoIpCity;
    }

    public static void main(String[] args) throws TException, IOException {

        TTransport transport = new TSimpleFileTransport("data/geo-ip-city.dat", false, true);
        TProtocol protocol = new TCompactProtocol(transport);

        protocol.writeStructBegin(new TStruct());
        protocol.writeMessageBegin(new TMessage());
        // TODO: ?
        protocol.writeMessageEnd();


        protocol.writeStructEnd();

    }

}
