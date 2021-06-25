package mayton.probeavro.emp;

import mayton.probeavro.geoip.GeoIpCityAvroEntity;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.UUID;

public class EmpApp {

    static Logger logger = LoggerFactory.getLogger(EmpApp.class);

    public static void main(String[] args) throws IOException {
        Emp emp1 = Emp.newBuilder()
                .setEMPNO(555)
                .setENAME("KING")
                .setDEPTNO(0)
                .setCOMM(100.5).setHIREDATE("16/02/1954")
                .setMGR(null)
                .setJOB(JOBEnum.SALESMAN)
                .setSAL(ByteBuffer.allocate(10))
                .setDEPTNO(44).build();

        Debt debt = Debt.newBuilder()
                .setDEBTNO(UUID.randomUUID().toString())
                .setDEBTNAME("SALES")
                .setLOC("New York")
                .build();

        DebtEmp debtEmp = DebtEmp.newBuilder()
                .setDEBTNO(10)
                .setDEBTNAME("STORE")
                .setLOC("Boston")
                .setEMPS(EMPSRecord.newBuilder()
                        .setENAME("John")
                        .build())
                .build();

        DebtAggregator debtAggregator = DebtAggregator.newBuilder()
                .setDEBTNO(10)
                .setEMPCollection(Collections.emptyList())
                .build();



        BinaryMessageEncoder<Emp> binaryMessageEncoder = Emp.getEncoder();
        BinaryMessageDecoder<Emp> binaryMessageDecoder = Emp.getDecoder();

        try(FileOutputStream os = new FileOutputStream("dat/emp1.avro")) {
            binaryMessageEncoder.encode(emp1, os);

            Emp empDecoded = binaryMessageDecoder.decode(new FileInputStream("dat/emp1.avro"));
            logger.info("{}", empDecoded);

        } catch (IOException ex) {
            logger.error("!", ex);
        }

        Schema schema = Emp.getClassSchema();
        logger.info("schema = {}" ,schema);

        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();


    }

}
