package mayton.compression;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class NumericUtilsPropertyBasedTest {

    @Property
    public void negativeTestDetectWidthInBits(@InRange(minInt = 1, maxLong = Integer.MAX_VALUE) int i) {
        assertTrue(NumericUtils.detectWidthInBits(i) > 0);
    }

}
