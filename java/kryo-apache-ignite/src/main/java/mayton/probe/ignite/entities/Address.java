package mayton.probe.ignite.entities;

import org.apache.ignite.binary.BinaryObjectException;
import org.apache.ignite.binary.BinaryReader;
import org.apache.ignite.binary.BinaryWriter;
import org.apache.ignite.binary.Binarylizable;

public class Address implements Binarylizable {
    /**
     * Street.
     */
    private String street;

    /**
     * ZIP code.
     */
    private int zip;

    /**
     * Required for binary deserialization.
     */
    public Address() {
        // No-op.
    }

    /**
     * @param street Street.
     * @param zip    ZIP code.
     */
    public Address(String street, int zip) {
        this.street = street;
        this.zip = zip;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeBinary(BinaryWriter writer) throws BinaryObjectException {
        writer.writeString("street", street);
        writer.writeInt("zip", zip);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readBinary(BinaryReader reader) throws BinaryObjectException {
        street = reader.readString("street");
        zip = reader.readInt("zip");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Address [street=" + street +
                ", zip=" + zip + ']';
    }

}
