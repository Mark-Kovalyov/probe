package mayton.probe.udp;

import com.github.soulaway.beecoder.BeeCoder;
import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;

import static com.github.soulaway.beecoder.BeeCoder.INSTANCE;
import static org.junit.Assert.assertArrayEquals;

public class BeeCoderTest {

    static Logger logger;
    static BeeCoder beeCoder;

    @BeforeClass
    public static void beforeClass() {
        BasicConfigurator.configure();
        logger = LoggerFactory.getLogger(BeeCoderTest.class);
        beeCoder = INSTANCE;
    }

    @Test
    public void test() throws IOException, ClassNotFoundException {
        List<Object> list2 = new LinkedList<>();
        list2.add(new AbstractMap.SimpleEntry("id", "9fc6c2da-e8c5-48d5-9e9a-601b2734e9a1"));

        List<Object> list = new LinkedList<>();
        list.add(new AbstractMap.SimpleEntry("t", "aa"));
        list.add(new AbstractMap.SimpleEntry("y", "q"));
        list.add(new AbstractMap.SimpleEntry("q", "ping"));
        list.add(new AbstractMap.SimpleEntry("a", list2));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(list);
        oos.flush();
        byte[] oosArray = bos.toByteArray();

        logger.info("::[1] {}", new String(oosArray));

        assertArrayEquals(new byte[30], oosArray);

        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
        ObjectOutputStream oos2 = new ObjectOutputStream(bos2);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(oosArray));

        beeCoder.encodeStream(ois, oos2);

        byte[] oos2array = bos2.toByteArray();

        logger.info("::[2] {}", new String(oos2array));
    }

}
