package mayton.probe.ignite.cachestore;

import mayton.probe.ignite.entities.BiTemporalValue;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
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
import java.util.Map;

public class CacheStoreAvroBiTemporalValue implements CacheStore<Long, BiTemporalValue> {

    static Logger logger = LoggerFactory.getLogger(CacheStoreAvroBiTemporalValue.class);

    static final String IGNITE_STORAGE = "/ignite-db/avro";
    static final String EXTENSION = ".avro";
    static final String AVRO_SCHEMA = "avro/bi-temporal-value.avsc";

    private String formatPath(long key) {
        return IGNITE_STORAGE + "/" + key + EXTENSION;
    }

    @Override
    public void loadCache(IgniteBiInClosure<Long, BiTemporalValue> clo, @Nullable Object... args) throws CacheLoaderException {

    }

    @Override
    public void sessionEnd(boolean commit) throws CacheWriterException {

    }

    @Override
    public BiTemporalValue load(Long key) throws CacheLoaderException {
        // TODO:
        BiTemporalValue biTemporalValue = null;
        Schema.Parser parser = new Schema.Parser();
        try {
            Schema schema = parser.parse(new FileInputStream(AVRO_SCHEMA));
            DatumReader<GenericRecord> reader = new GenericDatumReader<>();
            InputStream inStream = new FileInputStream(formatPath(key));
            //DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(schema);
            Decoder decoder = DecoderFactory.get().binaryDecoder(inStream, null);
        } catch (IOException e) {
            logger.error("IOEx", e);
        }
        return biTemporalValue;
    }

    @Override
    public Map<Long, BiTemporalValue> loadAll(Iterable<? extends Long> keys) throws CacheLoaderException {
        return null;
    }

    @Override
    public void write(Cache.Entry<? extends Long, ? extends BiTemporalValue> entry) throws CacheWriterException {
        BiTemporalValue biTemporalValue = entry.getValue();
        Schema.Parser parser = new Schema.Parser();
        try {
            Schema schema = parser.parse(new FileInputStream(AVRO_SCHEMA));

            GenericRecord datum = new GenericData.Record(schema);
            datum.put("beginTs", biTemporalValue.getBeginTs());
            datum.put("endTs",   biTemporalValue.getEndTs());
            datum.put("key",     biTemporalValue.getKeys());
            datum.put("values",  biTemporalValue.getValues());

            try(OutputStream outStream = new FileOutputStream(formatPath(entry.getKey()))){
                DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
                Encoder encoder = EncoderFactory.get().binaryEncoder(outStream, null);
                writer.write(datum, encoder);
                encoder.flush();
            }

        } catch (IOException e) {
            logger.error("IOEx", e);
        }
    }

    @Override
    public void writeAll(Collection<Cache.Entry<? extends Long, ? extends BiTemporalValue>> entries) throws CacheWriterException {

    }

    @Override
    public void delete(Object key) throws CacheWriterException {

    }

    @Override
    public void deleteAll(Collection<?> keys) throws CacheWriterException {

    }
}
