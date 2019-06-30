package mayton.probe.ignite.cachestore;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import mayton.probe.ignite.entities.BiTemporalValue;
import mayton.probe.ignite.entities.BiTemporalValueKryoSerializer;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.Cache;

import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CacheStoreBiTemporalValue implements CacheStore<Long, BiTemporalValue> {

    static Logger logger = LoggerFactory.getLogger(CacheStoreBiTemporalValue.class);

    static final String IGNITE_STORAGE = "/ignite-db";
    static final String EXTENSION = ".kryo";

    static private final ThreadLocal<Kryo> kryos = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(BiTemporalValue.class, new BiTemporalValueKryoSerializer());
        return kryo;
    });

    @Override
    public void loadCache(IgniteBiInClosure<Long, BiTemporalValue> clo, @Nullable Object... args) throws CacheLoaderException {
        logger.info("load cache with set of args");
    }

    @Override
    public void sessionEnd(boolean commit) throws CacheWriterException {
        logger.info("session end with commit = {}", commit);
    }

    @Override
    public BiTemporalValue load(Long key) throws CacheLoaderException {
        logger.info(":: load key = {}", key);
        Kryo kryo = kryos.get();
        BiTemporalValue biTemporalValue = null;
        try {
            InputStream is = new FileInputStream(formatPath(key));
            biTemporalValue = kryo.readObject(new Input(is), BiTemporalValue.class);
        } catch (FileNotFoundException e) {
            // Nothing
        }
        return biTemporalValue;
    }

    private String formatPath(long key) {
        return IGNITE_STORAGE + "/" + key + EXTENSION;
    }

    @Override
    public Map<Long, BiTemporalValue> loadAll(Iterable<? extends Long> keys) throws CacheLoaderException {
        logger.info(":: load all");
        return Collections.EMPTY_MAP;
    }

    @Override
    public void write(Cache.Entry<? extends Long, ? extends BiTemporalValue> entry) throws CacheWriterException {
        logger.info(":: write key = {}", entry.getKey());
        Kryo kryo = kryos.get();
        // TODO:
        try(Output output = new Output(new FileOutputStream(formatPath(entry.getKey())))) {
            kryo.writeObject(output, entry.getValue());
        } catch (IOException e) {
            logger.error(":: IOException", e);
        }
    }

    @Override
    public void writeAll(Collection<Cache.Entry<? extends Long, ? extends BiTemporalValue>> entries) throws CacheWriterException {
        for(Cache.Entry<? extends Long, ? extends BiTemporalValue> entry : entries) {
            write(entry);
        }
    }

    @Override
    public void delete(Object key) throws CacheWriterException {
        logger.info(":: delete key = {}", key.toString());
        if (!new File(formatPath((Long)key)).delete()) {
            logger.error(":: unable to delete key = {}", key.toString());
        }
    }

    @Override
    public void deleteAll(Collection<?> keys) throws CacheWriterException {
        logger.info(":: delete all");

    }
}
