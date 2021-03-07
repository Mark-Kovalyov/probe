package mayton.probe.ignite.cachestore;

import mayton.probe.ignite.entities.BiTemporalValue;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.apache.orc.CompressionKind;
import org.apache.orc.OrcFile;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CacheStoreOrcBiTemporalValue implements CacheStore<Long, BiTemporalValue> {

    static Logger logger = LoggerFactory.getLogger(CacheStoreOrcBiTemporalValue.class);

    static final String IGNITE_STORAGE = "/ignite-db/orc";
    static final String EXTENSION = ".orc";

    private String formatPath(long key) {
        return IGNITE_STORAGE + "/" + key + EXTENSION;
    }

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
        BiTemporalValue biTemporalValue = null;
        try(InputStream is = new FileInputStream(formatPath(key))) {
            // TODO:
            byte[] bytes = IOUtils.toByteArray(is);

        } catch (IOException e) {
            logger.error(":: IOException", e);
        }
        return biTemporalValue;
    }

    @Override
    public Map<Long, BiTemporalValue> loadAll(Iterable<? extends Long> keys) throws CacheLoaderException {
        logger.info(":: load all");
        return Collections.EMPTY_MAP;
    }

    @Override
    public void write(Cache.Entry<? extends Long, ? extends BiTemporalValue> entry) throws CacheWriterException {
        logger.info(":: [1] write key = {}", entry.getKey());
        BiTemporalValue biTemporalValue = entry.getValue();
        TypeDescription schema = TypeDescription.fromString("struct<t1:bigint,t2:bigint,key:bigint,value:double>");
        Configuration configuration = new Configuration();
        String fp = formatPath(entry.getKey());
        logger.info(":: [1.1] formattedPath = {}", fp);
        try(
            Writer writer = OrcFile.createWriter(new Path(fp),
                    OrcFile.writerOptions(configuration)
                            .setSchema(schema)
                            .overwrite(true)
                            .compress(CompressionKind.NONE))) {

            logger.info(":: [2]");

            VectorizedRowBatch vectorizedRowBatch = schema.createRowBatch(biTemporalValue.getSize());
            LongColumnVector beginTs  = (LongColumnVector) vectorizedRowBatch.cols[0];
            LongColumnVector endTs    = (LongColumnVector) vectorizedRowBatch.cols[1];
            LongColumnVector keys     = (LongColumnVector) vectorizedRowBatch.cols[2];
            DoubleColumnVector values = (DoubleColumnVector) vectorizedRowBatch.cols[3];

            for(int i = 0; i < biTemporalValue.getSize(); i++) {
                logger.info(":: [3] i = {}", i);

                int row = vectorizedRowBatch.size;
                vectorizedRowBatch.size++;
                beginTs.vector[row] = biTemporalValue.getBeginTs()[i];
                endTs.vector[row]   = biTemporalValue.getEndTs()[i];
                keys.vector[row]    = biTemporalValue.getKeys()[i];
                values.vector[row]  = biTemporalValue.getValues()[i];

                if (vectorizedRowBatch.size == vectorizedRowBatch.getMaxSize()) {
                    writer.addRowBatch(vectorizedRowBatch);
                    vectorizedRowBatch.reset();
                    logger.info(":: [4] addRowBatch");
                }
            }

            if (vectorizedRowBatch.size > 0) {
                writer.addRowBatch(vectorizedRowBatch);
                vectorizedRowBatch.reset();
                logger.info(":: [5] addRowBatch");
            }

        } catch (IOException e) {
            logger.error(":: [6] IOException",e);
        } finally {
            logger.error(":: [7] Finally");
        }
        logger.info(":: [8] finish");
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
