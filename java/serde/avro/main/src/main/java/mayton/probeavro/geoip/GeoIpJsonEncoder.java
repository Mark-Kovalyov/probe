package mayton.probeavro.geoip;

import org.apache.avro.Schema;
import org.apache.avro.data.Json;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Properties;

public class GeoIpJsonEncoder {

    static Logger logger = LoggerFactory.getLogger(GeoIpJsonEncoder.class);

    public static void main(String[] args) throws Exception {

        String userHome = System.getProperty("user.home");

        Properties properties = new Properties();
        properties.load(new FileInputStream(userHome + "/sensitive.properties"));

        Schema.Parser schemaParser = new Schema.Parser();
        Schema schema = schemaParser.parse(new FileInputStream(properties.getProperty("schema")));
        GenericRecord datum = new GenericData.Record(schema);
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);

        try(OutputStream outputStreamJson = new FileOutputStream(properties.getProperty("target.json"))) {

            Encoder jsonEncoder = EncoderFactory.get().jsonEncoder(schema, outputStreamJson);

            CSVParser parser = CSVParser.parse(new FileInputStream(properties.getProperty("source")), StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT
                            .withDelimiter(',')
                            .withFirstRecordAsHeader());

            Iterator<CSVRecord> irec = parser.iterator();


            Json.ObjectWriter objectWriter = new Json.ObjectWriter();

            jsonEncoder.writeArrayStart();



            while (irec.hasNext()) {

                jsonEncoder.startItem();

                CSVRecord rec = irec.next();
                int startIpNum = 0;//(int) NetworkUtils.parseIpV4(rec.get(0));
                int endIpNum = 0;//(int) NetworkUtils.parseIpV4(rec.get(1));


                jsonEncoder.writeString("startIpNum");
                jsonEncoder.writeInt(startIpNum);
                jsonEncoder.writeString("endIpNum");
                jsonEncoder.writeInt(endIpNum);

            }

            jsonEncoder.writeArrayStart();
            parser.close();
        }

    }



}
