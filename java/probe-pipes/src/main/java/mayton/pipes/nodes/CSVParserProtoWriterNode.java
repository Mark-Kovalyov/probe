package mayton.pipes.nodes;

import mayton.geo.GeoIpEntity;
import mayton.network.NetworkUtils;
import mayton.pipes.MTNode;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class CSVParserProtoWriterNode extends MTNode {

    public CSVParserProtoWriterNode(String threadName) {
        super(threadName);
    }

    @Override
    public void run() throws IOException {
        CSVParser parser = CSVParser.parse(pipedInputStream, StandardCharsets.UTF_8,
                CSVFormat.DEFAULT
                        .withDelimiter(',')
                        .withFirstRecordAsHeader());

        Iterator<CSVRecord> iterator = parser.iterator();
        int cnt = 0;
        while(iterator.hasNext()) {
            CSVRecord csvRecord = iterator.next();
            GeoIpEntity.GeoIpCity geoIpEntity = GeoIpEntity.GeoIpCity.newBuilder()
                    .setStartIpNum((int) NetworkUtils.parseIpV4(csvRecord.get(0)))
                    .setEndIpNum((int) NetworkUtils.parseIpV4(csvRecord.get(1)))
                    .setCountry(csvRecord.get(2))
                    .setRegion(csvRecord.get(3))
                    .setCity(csvRecord.get(4))
                    .setLatitude(0.0)
                    .setLongitude(0.0)
                    .build();
            geoIpEntity.writeDelimitedTo(pipedOutputStream);
            cnt++;
        }
        pipedOutputStream.close();
        logger.info("[2] copied {} records" , cnt);
    }
}
