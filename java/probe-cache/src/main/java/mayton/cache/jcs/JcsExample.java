package mayton.cache.jcs;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.exception.CacheException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;

import java.io.FileInputStream;
import java.io.Serializable;


public class JcsExample {

    static Logger logger = LogManager.getLogger("JcsExample");

    public static void main(String[] args) throws Exception {
        
        JcsExample example = new JcsExample();
        example.testCache();
    }

    private CacheAccess<String, City> cache = null;

    public JcsExample() {
        logger.info("Begin");
        try {
            cache = JCS.getInstance("default");
        } catch (CacheException e) {
            logger.error("Problem initializing cache: {}", e.getMessage());
        } finally {
            logger.info("End");
        }

    }

    public void putInCache(City city) {
        String key = city.name;
        try {
            cache.put(key, city);
        } catch (CacheException e) {
            logger.error("Problem putting city {} in the cache, for key {} \n {}", city.name, key, e.getMessage());
        }
    }

    public City retrieveFromCache(String cityKey) {
        return cache.get(cityKey);
    }

    public void testCache() {
        logger.traceEntry();
        City zurich = new City("Zürich", "Switzerland", 366765);
        putInCache(zurich);

        City berlin = new City("Berlin", "Germany", 3502000);
        putInCache(berlin);

        City johannesburg = new City("Johannesburg", "South Africa", 12200000);
        putInCache(johannesburg);

        City retrievedCity1 = retrieveFromCache("Berlin");
        if (retrievedCity1 != null) {
            logger.info(retrievedCity1.toString());
        } else {
            logger.info("No object was found in the cache for the key \"Berlin\"");
        }

        City retrievedCity2 = retrieveFromCache("New York");
        if (retrievedCity2 != null) {
            logger.info(retrievedCity2.toString());
        } else {
            logger.info("No object was found in the cache for the key \"New York\"");
        }
        logger.traceExit();
    }

    // defined as a nested inner class to reduce number of .java files in the example
    public class City implements Serializable {

        private static final long serialVersionUID = 6392376146163510146L;
        public String name;
        public String country;
        public int population;

        public City(String name, String country, int population) {
            this.name = name;
            this.country = country;
            this.population = population;
        }

        @Override
        public String toString() {
            return String.format("%s is a city in the country %s with a population of %d", name, country, population);
        }
    }
}