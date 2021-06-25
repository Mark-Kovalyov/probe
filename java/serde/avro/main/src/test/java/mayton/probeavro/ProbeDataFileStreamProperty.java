package mayton.probeavro;

import mayton.probeavro.geoip.GeoIpCity;
import mayton.probeavro.geoip.GeoIpCityAvroEntity;
import net.jqwik.api.*;
import net.jqwik.api.arbitraries.ListArbitrary;
import net.jqwik.api.arbitraries.StringArbitrary;
import net.jqwik.engine.properties.arbitraries.DefaultStringArbitrary;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Tag("binaryMessageEncoderDecoder")
@PropertyDefaults(tries = 300, afterFailure = AfterFailureMode.PREVIOUS_SEED)
public class ProbeDataFileStreamProperty {

    private Random random = new Random();

    @Property
    @Report(Reporting.GENERATED)
    boolean avroEncoderStreamSuccessfullyParsedByDecoder(@ForAll("RandomAvroEntities") GeoIpCityAvroEntity geoIpCityAvroEntity) throws IOException {
        BinaryMessageEncoder<GeoIpCityAvroEntity> binaryMessageEncoder = GeoIpCityAvroEntity.getEncoder();
        BinaryMessageDecoder<GeoIpCityAvroEntity> binaryMessageDecoder = GeoIpCityAvroEntity.getDecoder();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        binaryMessageEncoder.encode(geoIpCityAvroEntity, bos);
        bos.flush();
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        GeoIpCityAvroEntity recovered = binaryMessageDecoder.decode(bis);
        return recovered.equals(geoIpCityAvroEntity);
    }

    @Provide("RandomAvroEntities")
    Arbitrary<GeoIpCityAvroEntity> randomGeoEntity() {
        GeoIpCityAvroEntity entity = GeoIpCityAvroEntity.newBuilder()
                .setStartIpNum(random.nextInt())
                .setEndIpNum(random.nextInt())
                .setCountry("")
                .setRegion("")
                .setCity("")
                .setPostalCode("")
                .setLatitude(180.0 * random.nextDouble())
                .setLongitude(180.0 * random.nextDouble())
                .setDmaCode("")
                .setAreaCode("")
                .build();
        return Arbitraries.of(entity);
    }

}