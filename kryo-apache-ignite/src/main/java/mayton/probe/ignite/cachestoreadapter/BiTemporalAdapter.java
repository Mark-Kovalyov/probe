package mayton.probe.ignite.cachestoreadapter;

import mayton.probe.ignite.entities.BiTemporalValue;
import org.apache.ignite.cache.store.CacheStoreAdapter;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;

public class BiTemporalAdapter extends CacheStoreAdapter<Long, BiTemporalValue> {


    @Override
    public BiTemporalValue load(Long key) throws CacheLoaderException {
        return null;
    }

    @Override
    public void write(Cache.Entry<? extends Long, ? extends BiTemporalValue> entry) throws CacheWriterException {

    }

    @Override
    public void delete(Object key) throws CacheWriterException {

    }
}
