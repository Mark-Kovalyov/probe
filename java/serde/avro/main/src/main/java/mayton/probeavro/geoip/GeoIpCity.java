package mayton.probeavro.geoip;

public class GeoIpCity {

    String startIpNum;
    String endIpNum;
    String country;
    String region;
    String city;
    String postalCode;
    String latitude;
    String longitude;
    String dmaCode;
    String areaCode;

    public String getStartIpNum() {
        return startIpNum;
    }

    public void setStartIpNum(String startIpNum) {
        this.startIpNum = startIpNum;
    }

    public String getEndIpNum() {
        return endIpNum;
    }

    public void setEndIpNum(String endIpNum) {
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
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
    public String toString() {
        return "GeoIpCity{" +
                "startIpNum='" + startIpNum + '\'' +
                ", endIpNum='" + endIpNum + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", dmaCode='" + dmaCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                '}';
    }
}
