package mayton.geo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class GeoIpEntitySerializer extends Serializer<GeoIpEntity> {

    //    int startIpNum;
    //    int endIpNum;
    //    String country;
    //    String region;
    //    String city;

    //    String postalCode;
    //    double latitude;
    //    double longitude;
    //    String dmaCode;
    //    String areaCode;

    @Override
    public void write(Kryo kryo, Output output, GeoIpEntity geoIpEntity) {
        output.writeVarInt(geoIpEntity.startIpNum, true);
        output.writeVarInt(geoIpEntity.endIpNum, true);
        output.writeString(geoIpEntity.country);
        output.writeString(geoIpEntity.region);
        output.writeString(geoIpEntity.city);
        output.writeString(geoIpEntity.postalCode);
        output.writeDouble(geoIpEntity.latitude);
        output.writeDouble(geoIpEntity.longitude);
        output.writeString(geoIpEntity.dmaCode);
        output.writeString(geoIpEntity.areaCode);
    }

    @Override
    public GeoIpEntity read(Kryo kryo, Input input, Class<? extends GeoIpEntity> aClass) {
        GeoIpEntity geoIpEntity = new GeoIpEntity();
        geoIpEntity.setStartIpNum(input.readVarInt(true));
        geoIpEntity.setEndIpNum(input.readVarInt(true));
        geoIpEntity.setCountry(input.readString());
        geoIpEntity.setRegion(input.readString());
        geoIpEntity.setCity(input.readString());
        geoIpEntity.setPostalCode(input.readString());
        geoIpEntity.setLatitude(input.readDouble());
        geoIpEntity.setLongitude(input.readDouble());
        geoIpEntity.setDmaCode(input.readString());
        geoIpEntity.setAreaCode(input.readString());
        return geoIpEntity;
    }

}
