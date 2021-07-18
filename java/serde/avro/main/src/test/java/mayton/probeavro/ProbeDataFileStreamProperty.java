package mayton.probeavro;

import mayton.probeavro.geoip.GeoIpCity;
import mayton.probeavro.geoip.GeoIpCityAvroEntityV2;
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
    boolean avroEncoderStreamSuccessfullyParsedByDecoder(@ForAll("RandomAvroEntities") GeoIpCityAvroEntityV2 geoIpCityAvroEntity) throws IOException {
        BinaryMessageEncoder<GeoIpCityAvroEntityV2> binaryMessageEncoder = GeoIpCityAvroEntityV2.getEncoder();
        BinaryMessageDecoder<GeoIpCityAvroEntityV2> binaryMessageDecoder = GeoIpCityAvroEntityV2.getDecoder();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        binaryMessageEncoder.encode(geoIpCityAvroEntity, bos);
        bos.flush();
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        GeoIpCityAvroEntityV2 recovered = binaryMessageDecoder.decode(bis);
        return recovered.equals(geoIpCityAvroEntity);
    }

    @Provide("RandomAvroEntities")
    Arbitrary<GeoIpCityAvroEntityV2> randomGeoEntity() {
        GeoIpCityAvroEntityV2 entity = GeoIpCityAvroEntityV2.newBuilder()
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