package mayton.geo;

import com.google.protobuf.CodedOutputStream;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        GeoIpEntity.GeoIpCity london = GeoIpEntity.GeoIpCity.newBuilder()
                .setStartIpNum(0)
                .setEndIpNum(0xFFFFFFFF)
                .setCity("London")
                .setCountry("Brit")
                .setAreaCode("EN")
                .setDmaCode("UX")
                .setLatitude(0.1)
                .setLongitude(0.2)
                .setPostalCode("01234")
                .build();

        /*london.writeTo(new FileOutputStream("dat/london.dat"));

        london.writeDelimitedTo(new FileOutputStream("dat/london-delimited.dat"));

        CodedOutputStream codedOutputStream = CodedOutputStream.newInstance(new FileOutputStream("dat/london-coded.dat"));
        london.writeTo(codedOutputStream);
        codedOutputStream.flush();

        GeoIpEntity.GeoIpCity londonRecovered = GeoIpEntity.GeoIpCity.parseFrom(new FileInputStream("dat/london.dat"));*/

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        london.writeDelimitedTo(byteArrayOutputStream);
        london.writeDelimitedTo(byteArrayOutputStream);
        byteArrayOutputStream.flush();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        GeoIpEntity.GeoIpCity londonRecovered1 = GeoIpEntity.GeoIpCity.parseDelimitedFrom(byteArrayInputStream);
        GeoIpEntity.GeoIpCity londonRecovered2 = GeoIpEntity.GeoIpCity.parseDelimitedFrom(byteArrayInputStream);

        System.out.println(londonRecovered1.toString());

        System.out.println(londonRecovered2.toString());

    }
}
