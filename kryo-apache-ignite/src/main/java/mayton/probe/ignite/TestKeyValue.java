package mayton.probe.ignite;

import mayton.probe.ignite.entities.City;
import mayton.probe.ignite.entities.CityKey;


import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

public class TestKeyValue {

    public static void main(String[] args) {

        try (Ignite ignite = Ignition.start("config/ignite-config.xml")) {

            IgniteCache<CityKey, City> cityCache = ignite.cache("City");

            CityKey key = new CityKey(5, "NLD");

            //getting the city by ID and country code
            City city = cityCache.get(key);

            System.out.println(">> Updating Amsterdam record:");

            city.setPopulation(city.getPopulation() - 10_000);

            cityCache.put(key, city);

            System.out.println(cityCache.get(key));
        }
    }

}
