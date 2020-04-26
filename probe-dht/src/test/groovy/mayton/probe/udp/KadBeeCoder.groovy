package mayton.probe.udp

import com.github.soulaway.beecoder.BeeCoder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Specification

class KadBeeCoder extends Specification {

    static Logger logger
    static BeeCoder beeCoder

    def setupSpec() {
        logger = LoggerFactory.getLogger(KadBeeCoder.class)
        beeCoder = BeeCoder.INSTANCE
    }

    def setup() {

    }

    def encodeStream(ObjectInputStream ois) {

        return new byte[0]
    }

    // ping Query = {"t":"aa", "y":"q", "q":"ping", "a":{"id":"9fc6c2da-e8c5-48d5-9e9a-601b2734e9a1"}}
    // bencoded = d1:ad2:id20:9fc6c2da-e8c5-48d5-9e9a-601b2734e9a1:q4:ping1:t2:aa1:y1:qe
    def "Assume that PING encoded OK"() {
        // encodeStream(ObjectInputStream ois, OutputStream bos)
        given:
            List<Object> list2 = new LinkedList<>()
            list2.add(new AbstractMap.SimpleEntry("id", "9fc6c2da-e8c5-48d5-9e9a-601b2734e9a1"));

            List<Object> list = new LinkedList<>()
            list.add(new AbstractMap.SimpleEntry("t", "aa"));
            list.add(new AbstractMap.SimpleEntry("y", "q"));
            list.add(new AbstractMap.SimpleEntry("q", "ping"));
            list.add(new AbstractMap.SimpleEntry("a", list2));

            byte[] ethalon = "d1:ad2:id20:9fc6c2da-e8c5-48d5-9e9a-601b2734e9a1:q4:ping1:t2:aa1:y1:qe".getBytes()

        when:

            ByteArrayOutputStream bos = new ByteArrayOutputStream()
            ObjectOutputStream oos = new ObjectOutputStream(bos)
            oos.writeObject(list)
            oos.flush()
            byte[] oosArray = bos.toByteArray()

            ByteArrayOutputStream bos2 = new ByteArrayOutputStream();

            ObjectOutputStream oos2 = new ObjectOutputStream(bos2)
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(oosArray))

            beeCoder.encodeStream(ois, oos2)

            oos2.flush()


            logger.info(":: 1")

        then:
            bos2.toByteArray() == ethalon
    }

    // Response = {"t":"aa", "y":"r", "r": {"id":"a2d11ce2-23ee-42d0-8215-eb62e32fccd5"}}
    // bencoded = d1:rd2:id20:a2d11ce2-23ee-42d0-8215-eb62e32fccd5:t2:aa1:y1:re
    def "Assume that PONG decoded OK" () {
        // decodeStream(InputStream bis, ObjectOutputStream oos)
        int x = 0
    }

}