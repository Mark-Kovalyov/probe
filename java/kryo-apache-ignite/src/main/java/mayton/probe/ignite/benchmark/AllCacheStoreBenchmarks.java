package mayton.probe.ignite.benchmark;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import mayton.probe.ignite.cachestore.CacheStoreKryoBiTemporalValue;
import mayton.probe.ignite.entities.BiTemporalAttribute;
import mayton.probe.ignite.entities.BiTemporalAttributeKryoSerializer;
import mayton.probe.ignite.entities.BiTemporalValue;
import mayton.probe.ignite.entities.BiTemporalValueKryoSerializer;
import org.openjdk.jmh.annotations.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class AllCacheStoreBenchmarks {

    @Benchmark
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 100, batchSize = 10)
    @BenchmarkMode(Mode.Throughput)
    public void kryo() {

        Kryo kryo = new Kryo();
        kryo.register(BiTemporalValue.class, new BiTemporalValueKryoSerializer());
        kryo.register(BiTemporalAttribute.class, new BiTemporalAttributeKryoSerializer());
        BiTemporalValue biTemporalValue = new BiTemporalValue(1000);

        Random random = new Random();

        for(int k = 0;k < 1000 ; k++) {
            biTemporalValue.getBeginTs()[k] = random.nextLong();
            biTemporalValue.getEndTs()[k] = random.nextLong();
            biTemporalValue.getKeys()[k] = random.nextLong();
            biTemporalValue.getValues()[k] = random.nextDouble();
        }

        try(Output output = new Output(new FileOutputStream(CacheStoreKryoBiTemporalValue.formatPath(random.nextLong())))) {
            kryo.writeObject(output, biTemporalValue);
        } catch (IOException e) {
            // Nothing
        }

    }

    @Benchmark
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 100, batchSize = 10)
    @BenchmarkMode(Mode.Throughput)
    public void orc() {

    }

    @Benchmark
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 100, batchSize = 10)
    @BenchmarkMode(Mode.Throughput)
    public void avro() {

    }



}
