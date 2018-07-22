package mayton.probe;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class GeoIpCityPk implements Serializable {

    @Column(name = "STARTIPNUM")
    protected String startIpNum;

    @Column(name = "ENDIPNUM")
    protected String endIpNum;

    public GeoIpCityPk() {}

    public GeoIpCityPk(String startIpNum, String endIpNum) {
        this.startIpNum = startIpNum;
        this.endIpNum = endIpNum;
    }
}
