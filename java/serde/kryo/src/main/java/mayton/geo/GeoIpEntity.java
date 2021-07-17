package mayton.geo;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoIpEntity that = (GeoIpEntity) o;
        return startIpNum == that.startIpNum && endIpNum == that.endIpNum &&
                Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 &&
                Objects.equals(country, that.country) && Objects.equals(region, that.region) &&
                Objects.equals(city, that.city) && Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(dmaCode, that.dmaCode) && Objects.equals(areaCode, that.areaCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startIpNum, endIpNum, country, region, city, postalCode, latitude, longitude, dmaCode, areaCode);
    }

    @Override
    public String toString() {
        return "GeoIpEntity{" +
                "startIpNum=" + startIpNum +
                ", endIpNum=" + endIpNum +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", dmaCode='" + dmaCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                '}';
    }
}

