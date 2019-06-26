package mayton.probe.ignite;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import mayton.probe.ignite.entities.BiTemporal;
import mayton.probe.ignite.entities.BiTemporalKryoSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class TestKryo {

    public static void main(String[] args) throws IOException {
        Kryo kryo = new Kryo();
        kryo.register(BiTemporal.class, new BiTemporalKryoSerializer());

        BiTemporalKryoSerializer serializer = new BiTemporalKryoSerializer();

        BiTemporal biTemporal = new BiTemporal(100);

        Random random = new Random();

        for(int k = 0;k< 100 ; k++) {
            biTemporal.getBeginTs()[k] = random.nextLong();
            biTemporal.getEndTs()[k] = random.nextLong();
        }

        kryo.writeObject(new Output(new FileOutputStream("kryo/bi-temporal-06.dat")), biTemporal);
        kryo.writeObject(new Output(new FileOutputStream("kryo/bi-temporal-07.dat")), biTemporal, serializer);


    }

}
