package mayton.compression;

import org.apache.commons.lang3.Range;
import org.checkerframework.common.value.qual.IntRange;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 00 (byte)
 *
 * 00 01 (short/word)
 *
 * 00 01 02 03 (int/dword, IPv4, 32bit)
 *
 * 00 01 02 03 04 05 06 07 (long, double, 64bit, MMX register)
 *
 * 00 01 02 03 04 05 06 07 : 08 09 0A 0B 0C 0D 0E 0F (IPv6, MD5, 128 bit, XMM/SSE register)
 *
 * 00 01 02 03 04 05 06 07 : 08 09 0A 0B 0C 0D 0E 0F (AVX register, 256 bit)
 * 10 11 12 13 14 15 16 17 : 18 19 1A 1B 1C 1D 1E 1F
 */

public class NumericUtils {

    enum CPURegisters {

        BYTE(8,   "byte",        "BYTE"),
        WORD(16,  "short",       "WORD"),
        DWORD(32, "int",         "DWORD, IPv4, 32bit"),
        QWORD(64, "long/double", "QWORD, 64bit, MMX register"),
        XMM(128,  "",            "XMM/SSE register, IPv6, MD5, 128 bit, "),
        AVX(256,  "",            "AVX register, 256 bit");

        public final int bits;
        public final int requireBytes;
        public final String desc;
        public final String cjavaDataType;
        public final Range<BigInteger> range;

        CPURegisters(int bits, String cjavaDataType, String desc) {
            this.bits = bits;
            this.requireBytes = bits / 8;
            this.cjavaDataType = cjavaDataType;
            this.desc = desc;
            this.range = Range.between(BigInteger.ZERO, BigInteger.TWO.pow(bits));
        }
    }

    @IntRange(from = 0)
    public static int detectWidthInBits(int i) {
        checkArgument(i >= 0, "Unable to detect bit width for negative value = " + i);
        if (i == 0) return 0;
        int cnt = 0;
        for(int k = 1; k <= 32 ; k ++) {
            if ((i & 0x01) != 0) {
                cnt = k;
            }
            i >>= 1;
        }
        return cnt;
    }

    public static short safeCastToShort(int i) {
        if (i <= Short.MAX_VALUE && i >= Short.MIN_VALUE) {
            return (short) i;
        } else {
            throw new IllegalArgumentException("Unable to cast " + i + " to short");
        }
    }

    public static byte toByteExact(short i) {
        if (i <= Byte.MAX_VALUE && i >= Byte.MIN_VALUE) {
            return (byte) i;
        } else {
            throw new IllegalArgumentException("Unable to cast " + i + " to byte");
        }
    }

    public static byte toByteExact(int i) {
        if (i <= Byte.MAX_VALUE && i >= Byte.MIN_VALUE) {
            return (byte) i;
        } else {
            throw new IllegalArgumentException("Unable to cast " + i + " to byte");
        }
    }

    public static int toIntExact(long i) {
        return Math.toIntExact(i);
    }

}
