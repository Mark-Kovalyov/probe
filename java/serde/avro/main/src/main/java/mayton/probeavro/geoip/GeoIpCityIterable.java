package mayton.probeavro.geoip;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;

public class GeoIpCityIterable implements Iterable<GeoIpCity> {

    static Logger logger = LoggerFactory.getLogger(GeoIpCityIterable.class);

    private String path;

    public GeoIpCityIterable(String path) throws IOException {
        this.path = path;
    }

    @Override
    public Iterator<GeoIpCity> iterator() {
        try(CSVParser parser = CSVParser.parse(new FileInputStream(path), StandardCharsets.UTF_8, CSVFormat.DEFAULT)){
            Iterator<CSVRecord> iterator = parser.iterator();
            return new Iterator<>() {
                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public GeoIpCity next() {
                    CSVRecord record = iterator.next();
                    GeoIpCity geoIpCity = new GeoIpCity();
                    geoIpCity.setStartIpNum(record.get(0));
                    geoIpCity.setEndIpNum(record.get(1));
                    return geoIpCity;
                }
            };
        } catch (IOException ex) {
            logger.error(ex.toString());
            return Collections.emptyIterator();
        }

    }

}
