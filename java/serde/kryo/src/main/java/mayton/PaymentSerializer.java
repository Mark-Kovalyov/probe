package mayton;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class PaymentSerializer extends Serializer<Payment> {

    @Override
    public void write(Kryo kryo, Output output, Payment payment) {
        output.write(new byte[0]);
    }

    @Override
    public Payment read(Kryo kryo, Input input, Class<? extends Payment> aClass) {
        return new Payment();
    }
}
