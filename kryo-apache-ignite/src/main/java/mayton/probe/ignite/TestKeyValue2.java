package mayton.probe.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.binary.BinaryObjectBuilder;

public class TestKeyValue2 {

    private static final String CITY_CACHE_NAME = "CityCache";

    public static void main(String[] args) {

        try (Ignite ignite = Ignition.start("config/ignite-config.xml")) {

            IgniteCache<BinaryObject, BinaryObject> cityCacheBinary = ignite.cache(CITY_CACHE_NAME).withKeepBinary();

            BinaryObjectBuilder cityKeyBuilder = ignite.binary().builder("demo.model.CityKey");

            cityKeyBuilder.setField("ID", 5);
            cityKeyBuilder.setField("COUNTRYCODE", "NLD");

            BinaryObject amKey = cityKeyBuilder.build();

//            BinaryObject amsterdam = cityCache.get(amKey);
//
//            System.out.printf("%1s people live in %2s \n", amsterdam.field("population"), amsterdam.field("name"));
//
//            System.out.println(">> Updating Amsterdam record:");
//            amsterdam = amsterdam.toBuilder().setField("POPULATION", (int) amsterdam.field("POPULATION") - 10_000).build();
//
//            cityCache.put(amKey, amsterdam);

        }
    }
}
