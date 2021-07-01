package mayton.kafka.entities;

import com.google.protobuf.ByteString;

public class GeoIpEntity {

    int startIpNum;

    int endIpNum;

    String country;
    
    String region;

    String city;

    String postalCode;

    double latitude;

    double longitude;

    String dmaCode;

    String areaCode;

    public int getStartIpNum() {
        return startIpNum;
    }

    public void setStartIpNum(int startIpNum) {
        this.startIpNum = startIpNum;
    }

    public int getEndIpNum() {
        return endIpNum;
    }

    public void setEndIpNum(int endIpNum) {
        this.endIpNum = endIpNum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDmaCode() {
        return dmaCode;
    }

    public void setDmaCode(String dmaCode) {
        this.dmaCode = dmaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
