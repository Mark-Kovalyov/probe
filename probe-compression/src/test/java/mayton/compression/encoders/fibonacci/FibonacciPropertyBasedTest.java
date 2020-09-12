package mayton.compression.encoders.fibonacci;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(JUnitQuickcheck.class)
public class FibonacciPropertyBasedTest {

    @Property
    public void alwaysEndsWithOne(@InRange(minLong = 1, maxLong = Long.MAX_VALUE) long i) {
        String fibo = FibonacciUtils.encodeF(i);
        int length = fibo.length();
        assertEquals("1", fibo.substring(length - 1, length));
    }


}
