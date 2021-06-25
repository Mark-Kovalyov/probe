package mayton.probeavro;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.BlockingBinaryEncoder;
import org.apache.avro.io.EncoderFactory;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class RawBinaryEncoding {

    public static void main(String[] args) throws Exception {
        OutputStream os = new FileOutputStream("dat/raw-encoded.avro");
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(os, null);
        // TODO: Not sure is it correct sequence according to protocol
        encoder.startItem();
        encoder.writeString("Hello world");
        encoder.startItem();
        encoder.writeInt(555);
        encoder.startItem();
        encoder.writeDouble(3.14);
        encoder.flush();
        os.close();
    }

}
