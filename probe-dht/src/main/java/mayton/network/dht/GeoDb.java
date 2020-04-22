package mayton.network.dht;

import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie;
import mayton.network.NetworkUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.concurrent.ThreadSafe;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@ThreadSafe
public class GeoDb {

    private static Logger logger = LogManager.getLogger(GeoDb.class);

    private AhoCorasickDoubleArrayTrie<String> trie = new AhoCorasickDoubleArrayTrie<String>();

    private static GeoDb geoDb = null;

    private static List<GeoRecord> geoRecords = new ArrayList<>();

    private static String csvPath = "/storage/db/geo/maxmind/2010-10.MaxMind GeoIP City CSV Format/GeoIP-139_20101001/GeoIPCity.utf-8.csv";

    private GeoDb() {
        try {
            logger.info("init");
            CSVParser csvParser = new CSVParser(new FileReader(csvPath), CSVFormat.DEFAULT.withSkipHeaderRecord(true));
            Iterator<CSVRecord> i = csvParser.iterator();
            int cnt = 0;
            i.next();
            while(i.hasNext()) {
                CSVRecord record = i.next();
                String country = record.get(2);
                String city = record.get(4);
                long begin = NetworkUtils.parseIpV4(record.get(0));
                long end = NetworkUtils.parseIpV4(record.get(1));
                geoRecords.add(new GeoRecord(country, city, begin, end));
                cnt++;
            }
            csvParser.close();
            geoRecords.sort(GeoRecord.beginIpComparator);
            logger.info("init done, {} records loaded", cnt);
        } catch (NumberFormatException | IOException ex) {
            logger.error(ex);
        }
    }

    synchronized public static GeoDb getInstance() {
        if (geoDb == null) {
            geoDb = new GeoDb();
        }
        return geoDb;
    }

    public Optional<GeoRecord> findFirst(long ipv4) {
        // TODO: Optimize this fat and ugly loop into less complexity algorithm
        for(int i=0;i<geoRecords.size();i++) {
            GeoRecord gr = geoRecords.get(i);
            if (ipv4 >= gr.beginIp && ipv4 <= gr.endIp) {
                return Optional.of(gr);
            }
        }
        return Optional.empty();
    }


}
