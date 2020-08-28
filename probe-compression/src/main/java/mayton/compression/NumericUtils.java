package mayton.compression;

import java.math.BigInteger;

public class NumericUtils {

    enum CPURegisters {

        BYTE(1),
        WORD(2),
        DWORD(4),
        QWORD(8),
        MMX(16),
        SSE(32),
        AVX(64);

        public final int bits;

        CPURegisters(int bits) {
            this.bits = bits;
        }
    }

    public static int requiresBits(BigInteger bigInteger) {
        return 0;
    }

    public static int canPlacedInCPURegister(BigInteger bigInteger) {
        return 0;
    }

}
