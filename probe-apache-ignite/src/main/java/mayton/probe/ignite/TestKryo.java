package mayton.probe.ignite;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import mayton.probe.ignite.entities.BiTemporalAttribute;
import mayton.probe.ignite.entities.BiTemporalAttributeKryoSerializer;
import mayton.probe.ignite.entities.BiTemporalValue;
import mayton.probe.ignite.entities.BiTemporalValueKryoSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class TestKryo {

    static Logger logger = LoggerFactory.getLogger(TestClientConfig.class);

    public static void main(String[] args) throws IOException {

        System.out.println("java.version = " + System.getProperty("java.version"));

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

        Output output = new Output(new FileOutputStream("kryo/bi-temporal-value-01.kryo"));
        kryo.writeObject(output, biTemporalValue);
        output.close();

        BiTemporalAttribute biTemporalAttribute = new BiTemporalAttribute(1000);

        for(int k = 0;k < 1000 ; k++) {
            biTemporalAttribute.getBeginTs()[k] = random.nextLong();
            biTemporalAttribute.getEndTs()[k] = random.nextLong();
            biTemporalAttribute.getKeys()[k] = random.nextLong();
            biTemporalAttribute.getObjects()[k] = "X";
        }

        Output output2 = new Output(new FileOutputStream("kryo/bi-temporal-attribute-01.kryo"));
        kryo.writeObject(output2, biTemporalAttribute);
        output2.close();


    }

}
