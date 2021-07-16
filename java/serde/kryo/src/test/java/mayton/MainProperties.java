package mayton;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import mayton.geo.GeoIpEntity;
import mayton.geo.GeoIpEntitySerializer;
import net.jqwik.api.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@PropertyDefaults(
        tries = 300,
        afterFailure = AfterFailureMode.PREVIOUS_SEED,
        shrinking    = ShrinkingMode.BOUNDED,
        generation   = GenerationMode.AUTO,
        edgeCases    = EdgeCasesMode.FIRST
)
public class MainProperties {

    static Kryo kryo;

    static {
        kryo = new Kryo();
        kryo.register(GeoIpEntity.class, new GeoIpEntitySerializer());
    }

    Random random = new Random();

    @Property
    @Report(Reporting.GENERATED)
    boolean mainPropertyIsTrue(@ForAll("geo-ip-objects") GeoIpEntity geoIpEntity) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        kryo.writeObject(output, geoIpEntity);
        output.flush();
        bos.flush();
        GeoIpEntity recoveredGeoIpEntity = kryo.readObject(new Input(new ByteArrayInputStream(bos.toByteArray())), GeoIpEntity.class);
        boolean result = recoveredGeoIpEntity.equals(geoIpEntity);
        return result;
    }

    @Provide("geo-ip-objects")
    Arbitrary<GeoIpEntity> geoIpRandomObjects() {
        return Arbitraries.create(() -> {
            GeoIpEntity g = new GeoIpEntity();
            int start = random.nextInt(Integer.MAX_VALUE);
            int end = random.nextInt(Integer.MAX_VALUE);
            g.setStartIpNum(Math.min(start, end));
            g.setEndIpNum(Math.max(start, end));
            g.setCity(RandomStringUtils.random(15, true, false));
            g.setAreaCode(RandomStringUtils.random(3, true, false));
            g.setCountry(RandomStringUtils.random(10, true, false));
            g.setRegion(RandomStringUtils.random(4, true, false));
            g.setPostalCode(RandomStringUtils.random(5, false, true));
            g.setLatitude(-90.0 + 180.0 * random.nextDouble());
            g.setLongitude(-90.0 + 180.0 * random.nextDouble());
            g.setDmaCode("<FIXED DMA>");
            return g;
        });
    }

}
