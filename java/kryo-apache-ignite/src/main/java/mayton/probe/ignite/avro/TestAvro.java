package mayton.probe.ignite.avro;

import mayton.probe.ignite.cachestore.CacheStoreAvroBiTemporalValue;
import mayton.probe.ignite.entities.BiTemporalValue;
import org.apache.ignite.cache.CacheEntry;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.internal.processors.cache.CacheEntryImplEx;

public class TestAvro {

    public static void main(String[] args) {

        CacheStore<Long, BiTemporalValue> cacheStoreAvroBiTemporalValue = new CacheStoreAvroBiTemporalValue();



    }


}
