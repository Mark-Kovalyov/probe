package probe.mayton.orc;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.log4j.Logger;
import org.apache.orc.TypeDescription;

import java.io.FileReader;
import java.util.Iterator;

public class CsvToOrc {

    static Logger logger = Logger.getLogger(CsvToOrc.class);

    // Sample:
    // -----------------------------------------------------------------------------------------
    //startIpNum,endIpNum,country,region,city,postalCode,latitude,longitude,dmaCode,areaCode
    //1.0.0.0,1.7.255.255,"AU","","","",-27.0000,133.0000,,
    //1.9.0.0,1.9.255.255,"MY","","","",2.5000,112.5000,,
    //1.10.10.0,1.10.10.255,"AU","","","",-27.0000,133.0000,,
    //1.11.0.0,1.11.255.255,"KR","","","",37.0000,127.5000,,
    //1.12.0.0,1.15.255.255,"CN","","","",35.0000,105.0000,,
    //1.16.0.0,1.19.255.255,"KR","","","",37.0000,127.5000,,
    //1.21.0.0,1.21.255.255,"JP","","","",36.0000,138.0000,,
    //1.22.0.0,1.22.255.255,"IN","","","",20.0000,77.0000,,
    //1.23.0.0,1.23.15.255,"IN","02","Hyderabad","",17.3753,78.4744,,

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        TypeDescription schema = TypeDescription.fromString("struct<startIpNum:int,endIpNum:int>");

        CSVParser csvParser = new CSVParser(
                new FileReader("/db/geo/maxmind/2010-10.MaxMind GeoIP City CSV Format/GeoIP-139_20101001/GeoIPCity.utf-8.csv"),
                CSVFormat.DEFAULT);

        int cnt = 0;

        Iterator<CSVRecord> i = csvParser.iterator();
        while (i.hasNext()) {
            CSVRecord record = i.next();
            cnt++;
        }

        logger.info("Finished. Records : " + cnt);


    }

}
