package mayton.jdbc;

import javax.annotation.concurrent.Immutable;
import java.sql.RowId;

@Immutable
public class TnRowId implements RowId {

    private long rowId;

    public TnRowId(long rowId) {
        this.rowId = rowId;
    }

    public static long bytesToLong(byte[] b) {
        long result = 0;
        for (int i = 0; i < 8; i++) {
            result <<= 8;
            result |= (b[i] & 0xFF);
        }
        return result;
    }

    public static byte[] longToBytes(long l) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte)(l & 0xFF);
            l >>= 8;
        }
        return result;
    }

    @Override
    public byte[] getBytes() {
        return longToBytes(rowId);
    }
}
