package mayton.probeavro.geoip;

import org.apache.avro.data.Json;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GeoIpCityMain {

    static Logger logger = LoggerFactory.getLogger(GeoIpCityMain.class);

    public static void main(String[] args) throws IOException {

        GeoIpCityAvroEntityV2 entity = GeoIpCityAvroEntityV2.newBuilder()
                .setDmaCode("COD")
                .setAreaCode("ARE")
                .setLatitude(15.1)
                .setLongitude(0.0)
                .setPostalCode("29084375")
                .setRegion("01")
                .setCity("Kiev")
                .setCountry("UA")
                .setStartIpNum(0)
                .setEndIpNum(255).build();

        OutputStream outputStream = new FileOutputStream("output/geo-ip-entity-01.avro");
        GeoIpCityAvroEntityV2.getEncoder().encode(entity, outputStream);

        File file = new File("output/geo-ip-entity-02.avro");

        DatumWriter<GeoIpCityAvroEntityV2> datumWriter = new GenericDatumWriter<>(GeoIpCityAvroEntityV2.getClassSchema());

        DataFileWriter<GeoIpCityAvroEntityV2> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(GeoIpCityAvroEntityV2.getClassSchema(), file);
        dataFileWriter.append(entity);
        dataFileWriter.append(entity);
        dataFileWriter.close();


        Encoder jsonEncoder = EncoderFactory.get().jsonEncoder(GeoIpCityAvroEntityV2.getClassSchema(), new FileOutputStream("output/geo-ip-json-03.avro"));

        Json.ObjectWriter writer = new Json.ObjectWriter();
        writer.setSchema(GeoIpCityAvroEntityV2.getClassSchema());
        writer.write(entity, jsonEncoder);




    }
}
