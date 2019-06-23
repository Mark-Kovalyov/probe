package mayton.probe;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "GEOIPCITY")
public class GeoIpCity implements Serializable {

   @EmbeddedId
   private GeoIpCityPk geoIpCityPk;

   @Column(name = "COUNTRY")
   private String country;

   @Column(name = "REGION")
   private String region;

   @Column(name = "CITY")
   private String city;

   public GeoIpCity(GeoIpCityPk geoIpCityPk, String country, String region, String city) {
      this.geoIpCityPk = geoIpCityPk;
      this.country = country;
      this.region = region;
      this.city = city;
   }

   public GeoIpCityPk getGeoIpCityPk() {
      return geoIpCityPk;
   }

   public void setGeoIpCityPk(GeoIpCityPk geoIpCityPk) {
      this.geoIpCityPk = geoIpCityPk;
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
}
