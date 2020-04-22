package mayton.network.dht;

import mayton.network.NetworkUtils;

import javax.annotation.concurrent.Immutable;
import java.util.Comparator;

@Immutable
public final class GeoRecord {

    //public static Comparable<GeoRecord> beginIpComparable = (GeoRecord geoRecord) -> { return 0; };

    public static Comparator<GeoRecord> beginIpComparator = (GeoRecord r1, GeoRecord r2) -> { return Long.compare(r1.beginIp, r2.beginIp ); };

    public static Comparator<GeoRecord> endIpComparator = (GeoRecord r1, GeoRecord r2) -> { return Long.compare(r1.endIp, r2.endIp ); };

    public final String country;

    public final String city;

    public final long beginIp;

    public final long endIp;

    public GeoRecord(String country, String city, long beginIp, long endIp) {
        this.country = country;
        this.city = city;
        this.beginIp = beginIp;
        this.endIp = endIp;
    }

    @Override
    public String toString() {
        return country + " / " + city + " [ " + NetworkUtils.formatIpV4(beginIp) + ".." + NetworkUtils.formatIpV4(endIp) + " ]";
    }
}
