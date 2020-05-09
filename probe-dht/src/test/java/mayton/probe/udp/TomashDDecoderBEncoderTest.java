package mayton.probe.udp;

import bt.bencoding.BEEncoder;
import bt.bencoding.model.BEString;
import org.junit.Test;
import the8472.bencode.BDecoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class TomashDDecoderBEncoderTest {

    @Test
    public void test() {

    }

    @Test
    public void testEncoder() throws IOException {
        BEEncoder beEncoder = new BEEncoder();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        beEncoder.encode(new BEString("Hello".getBytes()), bos);

        BDecoder decoder = new BDecoder();
        //decoder.decode(new ByteBuffer(bos.toByteArray()));
    }

}
