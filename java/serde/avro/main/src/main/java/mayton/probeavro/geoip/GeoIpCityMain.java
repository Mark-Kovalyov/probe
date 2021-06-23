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

        GeoIpCityAvroEntity entity = GeoIpCityAvroEntity.newBuilder()
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
        GeoIpCityAvroEntity.getEncoder().encode(entity, outputStream);

        File file = new File("output/geo-ip-entity-02.avro");

        DatumWriter<GeoIpCityAvroEntity> datumWriter = new GenericDatumWriter<>(GeoIpCityAvroEntity.getClassSchema());

        DataFileWriter<GeoIpCityAvroEntity> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(GeoIpCityAvroEntity.getClassSchema(), file);
        dataFileWriter.append(entity);
        dataFileWriter.append(entity);
        dataFileWriter.close();


        Encoder jsonEncoder = EncoderFactory.get().jsonEncoder(GeoIpCityAvroEntity.getClassSchema(), new FileOutputStream("output/geo-ip-json-03.avro"));

        Json.ObjectWriter writer = new Json.ObjectWriter();
        writer.setSchema(GeoIpCityAvroEntity.getClassSchema());
        writer.write(entity, jsonEncoder);




    }
}
