package mayton.kafka.serde.avro;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class GeoIpCityAvroEntity extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
    private static final long serialVersionUID = -8169377971015846921L;
    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"GeoIpCityAvroEntity\",\"namespace\":\"mayton.probeavro.geoip\",\"fields\":[{\"name\":\"startIpNum\",\"type\":\"int\"},{\"name\":\"endIpNum\",\"type\":\"int\"},{\"name\":\"country\",\"type\":[\"string\",\"null\"]},{\"name\":\"region\",\"type\":[\"string\",\"null\"]},{\"name\":\"city\",\"type\":[\"string\",\"null\"]},{\"name\":\"postalCode\",\"type\":[\"string\",\"null\"]},{\"name\":\"latitude\",\"type\":\"double\"},{\"name\":\"longitude\",\"type\":\"double\"},{\"name\":\"dmaCode\",\"type\":\"string\",\"default\":\"0\"},{\"name\":\"areaCode\",\"type\":\"string\",\"default\":\"0\"}]}");
    public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

    private static SpecificData MODEL$ = new SpecificData();

    private static final BinaryMessageEncoder<GeoIpCityAvroEntity> ENCODER =
            new BinaryMessageEncoder<GeoIpCityAvroEntity>(MODEL$, SCHEMA$);

    private static final BinaryMessageDecoder<GeoIpCityAvroEntity> DECODER =
            new BinaryMessageDecoder<GeoIpCityAvroEntity>(MODEL$, SCHEMA$);

    /**
     * Return the BinaryMessageEncoder instance used by this class.
     * @return the message encoder used by this class
     */
    public static BinaryMessageEncoder<GeoIpCityAvroEntity> getEncoder() {
        return ENCODER;
    }

    /**
     * Return the BinaryMessageDecoder instance used by this class.
     * @return the message decoder used by this class
     */
    public static BinaryMessageDecoder<GeoIpCityAvroEntity> getDecoder() {
        return DECODER;
    }

    /**
     * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
     * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
     * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
     */
    public static BinaryMessageDecoder<GeoIpCityAvroEntity> createDecoder(SchemaStore resolver) {
        return new BinaryMessageDecoder<GeoIpCityAvroEntity>(MODEL$, SCHEMA$, resolver);
    }

    /**
     * Serializes this GeoIpCityAvroEntity to a ByteBuffer.
     * @return a buffer holding the serialized data for this instance
     * @throws java.io.IOException if this instance could not be serialized
     */
    public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
        return ENCODER.encode(this);
    }

    /**
     * Deserializes a GeoIpCityAvroEntity from a ByteBuffer.
     * @param b a byte buffer holding serialized data for an instance of this class
     * @return a GeoIpCityAvroEntity instance decoded from the given buffer
     * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
     */
    public static GeoIpCityAvroEntity fromByteBuffer(
            java.nio.ByteBuffer b) throws java.io.IOException {
        return DECODER.decode(b);
    }

    private int startIpNum;
    private int endIpNum;
    private java.lang.CharSequence country;
    private java.lang.CharSequence region;
    private java.lang.CharSequence city;
    private java.lang.CharSequence postalCode;
    private double latitude;
    private double longitude;
    private java.lang.CharSequence dmaCode;
    private java.lang.CharSequence areaCode;

    /**
     * Default constructor.  Note that this does not initialize fields
     * to their default values from the schema.  If that is desired then
     * one should use <code>newBuilder()</code>.
     */
    public GeoIpCityAvroEntity() {}

    /**
     * All-args constructor.
     * @param startIpNum The new value for startIpNum
     * @param endIpNum The new value for endIpNum
     * @param country The new value for country
     * @param region The new value for region
     * @param city The new value for city
     * @param postalCode The new value for postalCode
     * @param latitude The new value for latitude
     * @param longitude The new value for longitude
     * @param dmaCode The new value for dmaCode
     * @param areaCode The new value for areaCode
     */
    public GeoIpCityAvroEntity(java.lang.Integer startIpNum, java.lang.Integer endIpNum, java.lang.CharSequence country, java.lang.CharSequence region, java.lang.CharSequence city, java.lang.CharSequence postalCode, java.lang.Double latitude, java.lang.Double longitude, java.lang.CharSequence dmaCode, java.lang.CharSequence areaCode) {
        this.startIpNum = startIpNum;
        this.endIpNum = endIpNum;
        this.country = country;
        this.region = region;
        this.city = city;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dmaCode = dmaCode;
        this.areaCode = areaCode;
    }

    public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
    public org.apache.avro.Schema getSchema() { return SCHEMA$; }
    // Used by DatumWriter.  Applications should not call.
    public java.lang.Object get(int field$) {
        switch (field$) {
            case 0: return startIpNum;
            case 1: return endIpNum;
            case 2: return country;
            case 3: return region;
            case 4: return city;
            case 5: return postalCode;
            case 6: return latitude;
            case 7: return longitude;
            case 8: return dmaCode;
            case 9: return areaCode;
            default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
        }
    }

    // Used by DatumReader.  Applications should not call.
    @SuppressWarnings(value="unchecked")
    public void put(int field$, java.lang.Object value$) {
        switch (field$) {
            case 0: startIpNum = (java.lang.Integer)value$; break;
            case 1: endIpNum = (java.lang.Integer)value$; break;
            case 2: country = (java.lang.CharSequence)value$; break;
            case 3: region = (java.lang.CharSequence)value$; break;
            case 4: city = (java.lang.CharSequence)value$; break;
            case 5: postalCode = (java.lang.CharSequence)value$; break;
            case 6: latitude = (java.lang.Double)value$; break;
            case 7: longitude = (java.lang.Double)value$; break;
            case 8: dmaCode = (java.lang.CharSequence)value$; break;
            case 9: areaCode = (java.lang.CharSequence)value$; break;
            default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
        }
    }

    /**
     * Gets the value of the 'startIpNum' field.
     * @return The value of the 'startIpNum' field.
     */
    public int getStartIpNum() {
        return startIpNum;
    }


    /**
     * Sets the value of the 'startIpNum' field.
     * @param value the value to set.
     */
    public void setStartIpNum(int value) {
        this.startIpNum = value;
    }

    /**
     * Gets the value of the 'endIpNum' field.
     * @return The value of the 'endIpNum' field.
     */
    public int getEndIpNum() {
        return endIpNum;
    }


    /**
     * Sets the value of the 'endIpNum' field.
     * @param value the value to set.
     */
    public void setEndIpNum(int value) {
        this.endIpNum = value;
    }

    /**
     * Gets the value of the 'country' field.
     * @return The value of the 'country' field.
     */
    public java.lang.CharSequence getCountry() {
        return country;
    }


    /**
     * Sets the value of the 'country' field.
     * @param value the value to set.
     */
    public void setCountry(java.lang.CharSequence value) {
        this.country = value;
    }

    /**
     * Gets the value of the 'region' field.
     * @return The value of the 'region' field.
     */
    public java.lang.CharSequence getRegion() {
        return region;
    }


    /**
     * Sets the value of the 'region' field.
     * @param value the value to set.
     */
    public void setRegion(java.lang.CharSequence value) {
        this.region = value;
    }

    /**
     * Gets the value of the 'city' field.
     * @return The value of the 'city' field.
     */
    public java.lang.CharSequence getCity() {
        return city;
    }


    /**
     * Sets the value of the 'city' field.
     * @param value the value to set.
     */
    public void setCity(java.lang.CharSequence value) {
        this.city = value;
    }

    /**
     * Gets the value of the 'postalCode' field.
     * @return The value of the 'postalCode' field.
     */
    public java.lang.CharSequence getPostalCode() {
        return postalCode;
    }


    /**
     * Sets the value of the 'postalCode' field.
     * @param value the value to set.
     */
    public void setPostalCode(java.lang.CharSequence value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the 'latitude' field.
     * @return The value of the 'latitude' field.
     */
    public double getLatitude() {
        return latitude;
    }


    /**
     * Sets the value of the 'latitude' field.
     * @param value the value to set.
     */
    public void setLatitude(double value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the 'longitude' field.
     * @return The value of the 'longitude' field.
     */
    public double getLongitude() {
        return longitude;
    }


    /**
     * Sets the value of the 'longitude' field.
     * @param value the value to set.
     */
    public void setLongitude(double value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the 'dmaCode' field.
     * @return The value of the 'dmaCode' field.
     */
    public java.lang.CharSequence getDmaCode() {
        return dmaCode;
    }


    /**
     * Sets the value of the 'dmaCode' field.
     * @param value the value to set.
     */
    public void setDmaCode(java.lang.CharSequence value) {
        this.dmaCode = value;
    }

    /**
     * Gets the value of the 'areaCode' field.
     * @return The value of the 'areaCode' field.
     */
    public java.lang.CharSequence getAreaCode() {
        return areaCode;
    }


    /**
     * Sets the value of the 'areaCode' field.
     * @param value the value to set.
     */
    public void setAreaCode(java.lang.CharSequence value) {
        this.areaCode = value;
    }

    /**
     * Creates a new GeoIpCityAvroEntity RecordBuilder.
     * @return A new GeoIpCityAvroEntity RecordBuilder
     */
    public static GeoIpCityAvroEntity.Builder newBuilder() {
        return new GeoIpCityAvroEntity.Builder();
    }

    /**
     * Creates a new GeoIpCityAvroEntity RecordBuilder by copying an existing Builder.
     * @param other The existing builder to copy.
     * @return A new GeoIpCityAvroEntity RecordBuilder
     */
    public static GeoIpCityAvroEntity.Builder newBuilder(GeoIpCityAvroEntity.Builder other) {
        if (other == null) {
            return new GeoIpCityAvroEntity.Builder();
        } else {
            return new GeoIpCityAvroEntity.Builder(other);
        }
    }

    /**
     * Creates a new GeoIpCityAvroEntity RecordBuilder by copying an existing GeoIpCityAvroEntity instance.
     * @param other The existing instance to copy.
     * @return A new GeoIpCityAvroEntity RecordBuilder
     */
    public static GeoIpCityAvroEntity.Builder newBuilder(GeoIpCityAvroEntity other) {
        if (other == null) {
            return new GeoIpCityAvroEntity.Builder();
        } else {
            return new GeoIpCityAvroEntity.Builder(other);
        }
    }

    /**
     * RecordBuilder for GeoIpCityAvroEntity instances.
     */
    @org.apache.avro.specific.AvroGenerated
    public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<GeoIpCityAvroEntity>
            implements org.apache.avro.data.RecordBuilder<GeoIpCityAvroEntity> {

        private int startIpNum;
        private int endIpNum;
        private java.lang.CharSequence country;
        private java.lang.CharSequence region;
        private java.lang.CharSequence city;
        private java.lang.CharSequence postalCode;
        private double latitude;
        private double longitude;
        private java.lang.CharSequence dmaCode;
        private java.lang.CharSequence areaCode;

        /** Creates a new Builder */
        private Builder() {
            super(SCHEMA$);
        }

        /**
         * Creates a Builder by copying an existing Builder.
         * @param other The existing Builder to copy.
         */
        private Builder(GeoIpCityAvroEntity.Builder other) {
            super(other);
            if (isValidValue(fields()[0], other.startIpNum)) {
                this.startIpNum = data().deepCopy(fields()[0].schema(), other.startIpNum);
                fieldSetFlags()[0] = other.fieldSetFlags()[0];
            }
            if (isValidValue(fields()[1], other.endIpNum)) {
                this.endIpNum = data().deepCopy(fields()[1].schema(), other.endIpNum);
                fieldSetFlags()[1] = other.fieldSetFlags()[1];
            }
            if (isValidValue(fields()[2], other.country)) {
                this.country = data().deepCopy(fields()[2].schema(), other.country);
                fieldSetFlags()[2] = other.fieldSetFlags()[2];
            }
            if (isValidValue(fields()[3], other.region)) {
                this.region = data().deepCopy(fields()[3].schema(), other.region);
                fieldSetFlags()[3] = other.fieldSetFlags()[3];
            }
            if (isValidValue(fields()[4], other.city)) {
                this.city = data().deepCopy(fields()[4].schema(), other.city);
                fieldSetFlags()[4] = other.fieldSetFlags()[4];
            }
            if (isValidValue(fields()[5], other.postalCode)) {
                this.postalCode = data().deepCopy(fields()[5].schema(), other.postalCode);
                fieldSetFlags()[5] = other.fieldSetFlags()[5];
            }
            if (isValidValue(fields()[6], other.latitude)) {
                this.latitude = data().deepCopy(fields()[6].schema(), other.latitude);
                fieldSetFlags()[6] = other.fieldSetFlags()[6];
            }
            if (isValidValue(fields()[7], other.longitude)) {
                this.longitude = data().deepCopy(fields()[7].schema(), other.longitude);
                fieldSetFlags()[7] = other.fieldSetFlags()[7];
            }
            if (isValidValue(fields()[8], other.dmaCode)) {
                this.dmaCode = data().deepCopy(fields()[8].schema(), other.dmaCode);
                fieldSetFlags()[8] = other.fieldSetFlags()[8];
            }
            if (isValidValue(fields()[9], other.areaCode)) {
                this.areaCode = data().deepCopy(fields()[9].schema(), other.areaCode);
                fieldSetFlags()[9] = other.fieldSetFlags()[9];
            }
        }

        /**
         * Creates a Builder by copying an existing GeoIpCityAvroEntity instance
         * @param other The existing instance to copy.
         */
        private Builder(GeoIpCityAvroEntity other) {
            super(SCHEMA$);
            if (isValidValue(fields()[0], other.startIpNum)) {
                this.startIpNum = data().deepCopy(fields()[0].schema(), other.startIpNum);
                fieldSetFlags()[0] = true;
            }
            if (isValidValue(fields()[1], other.endIpNum)) {
                this.endIpNum = data().deepCopy(fields()[1].schema(), other.endIpNum);
                fieldSetFlags()[1] = true;
            }
            if (isValidValue(fields()[2], other.country)) {
                this.country = data().deepCopy(fields()[2].schema(), other.country);
                fieldSetFlags()[2] = true;
            }
            if (isValidValue(fields()[3], other.region)) {
                this.region = data().deepCopy(fields()[3].schema(), other.region);
                fieldSetFlags()[3] = true;
            }
            if (isValidValue(fields()[4], other.city)) {
                this.city = data().deepCopy(fields()[4].schema(), other.city);
                fieldSetFlags()[4] = true;
            }
            if (isValidValue(fields()[5], other.postalCode)) {
                this.postalCode = data().deepCopy(fields()[5].schema(), other.postalCode);
                fieldSetFlags()[5] = true;
            }
            if (isValidValue(fields()[6], other.latitude)) {
                this.latitude = data().deepCopy(fields()[6].schema(), other.latitude);
                fieldSetFlags()[6] = true;
            }
            if (isValidValue(fields()[7], other.longitude)) {
                this.longitude = data().deepCopy(fields()[7].schema(), other.longitude);
                fieldSetFlags()[7] = true;
            }
            if (isValidValue(fields()[8], other.dmaCode)) {
                this.dmaCode = data().deepCopy(fields()[8].schema(), other.dmaCode);
                fieldSetFlags()[8] = true;
            }
            if (isValidValue(fields()[9], other.areaCode)) {
                this.areaCode = data().deepCopy(fields()[9].schema(), other.areaCode);
                fieldSetFlags()[9] = true;
            }
        }

        /**
         * Gets the value of the 'startIpNum' field.
         * @return The value.
         */
        public int getStartIpNum() {
            return startIpNum;
        }


        /**
         * Sets the value of the 'startIpNum' field.
         * @param value The value of 'startIpNum'.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder setStartIpNum(int value) {
            validate(fields()[0], value);
            this.startIpNum = value;
            fieldSetFlags()[0] = true;
            return this;
        }

        /**
         * Checks whether the 'startIpNum' field has been set.
         * @return True if the 'startIpNum' field has been set, false otherwise.
         */
        public boolean hasStartIpNum() {
            return fieldSetFlags()[0];
        }


        /**
         * Clears the value of the 'startIpNum' field.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder clearStartIpNum() {
            fieldSetFlags()[0] = false;
            return this;
        }

        /**
         * Gets the value of the 'endIpNum' field.
         * @return The value.
         */
        public int getEndIpNum() {
            return endIpNum;
        }


        /**
         * Sets the value of the 'endIpNum' field.
         * @param value The value of 'endIpNum'.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder setEndIpNum(int value) {
            validate(fields()[1], value);
            this.endIpNum = value;
            fieldSetFlags()[1] = true;
            return this;
        }

        /**
         * Checks whether the 'endIpNum' field has been set.
         * @return True if the 'endIpNum' field has been set, false otherwise.
         */
        public boolean hasEndIpNum() {
            return fieldSetFlags()[1];
        }


        /**
         * Clears the value of the 'endIpNum' field.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder clearEndIpNum() {
            fieldSetFlags()[1] = false;
            return this;
        }

        /**
         * Gets the value of the 'country' field.
         * @return The value.
         */
        public java.lang.CharSequence getCountry() {
            return country;
        }


        /**
         * Sets the value of the 'country' field.
         * @param value The value of 'country'.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder setCountry(java.lang.CharSequence value) {
            validate(fields()[2], value);
            this.country = value;
            fieldSetFlags()[2] = true;
            return this;
        }

        /**
         * Checks whether the 'country' field has been set.
         * @return True if the 'country' field has been set, false otherwise.
         */
        public boolean hasCountry() {
            return fieldSetFlags()[2];
        }


        /**
         * Clears the value of the 'country' field.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder clearCountry() {
            country = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        /**
         * Gets the value of the 'region' field.
         * @return The value.
         */
        public java.lang.CharSequence getRegion() {
            return region;
        }


        /**
         * Sets the value of the 'region' field.
         * @param value The value of 'region'.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder setRegion(java.lang.CharSequence value) {
            validate(fields()[3], value);
            this.region = value;
            fieldSetFlags()[3] = true;
            return this;
        }

        /**
         * Checks whether the 'region' field has been set.
         * @return True if the 'region' field has been set, false otherwise.
         */
        public boolean hasRegion() {
            return fieldSetFlags()[3];
        }


        /**
         * Clears the value of the 'region' field.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder clearRegion() {
            region = null;
            fieldSetFlags()[3] = false;
            return this;
        }

        /**
         * Gets the value of the 'city' field.
         * @return The value.
         */
        public java.lang.CharSequence getCity() {
            return city;
        }


        /**
         * Sets the value of the 'city' field.
         * @param value The value of 'city'.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder setCity(java.lang.CharSequence value) {
            validate(fields()[4], value);
            this.city = value;
            fieldSetFlags()[4] = true;
            return this;
        }

        /**
         * Checks whether the 'city' field has been set.
         * @return True if the 'city' field has been set, false otherwise.
         */
        public boolean hasCity() {
            return fieldSetFlags()[4];
        }


        /**
         * Clears the value of the 'city' field.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder clearCity() {
            city = null;
            fieldSetFlags()[4] = false;
            return this;
        }

        /**
         * Gets the value of the 'postalCode' field.
         * @return The value.
         */
        public java.lang.CharSequence getPostalCode() {
            return postalCode;
        }


        /**
         * Sets the value of the 'postalCode' field.
         * @param value The value of 'postalCode'.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder setPostalCode(java.lang.CharSequence value) {
            validate(fields()[5], value);
            this.postalCode = value;
            fieldSetFlags()[5] = true;
            return this;
        }

        /**
         * Checks whether the 'postalCode' field has been set.
         * @return True if the 'postalCode' field has been set, false otherwise.
         */
        public boolean hasPostalCode() {
            return fieldSetFlags()[5];
        }


        /**
         * Clears the value of the 'postalCode' field.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder clearPostalCode() {
            postalCode = null;
            fieldSetFlags()[5] = false;
            return this;
        }

        /**
         * Gets the value of the 'latitude' field.
         * @return The value.
         */
        public double getLatitude() {
            return latitude;
        }


        /**
         * Sets the value of the 'latitude' field.
         * @param value The value of 'latitude'.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder setLatitude(double value) {
            validate(fields()[6], value);
            this.latitude = value;
            fieldSetFlags()[6] = true;
            return this;
        }

        /**
         * Checks whether the 'latitude' field has been set.
         * @return True if the 'latitude' field has been set, false otherwise.
         */
        public boolean hasLatitude() {
            return fieldSetFlags()[6];
        }


        /**
         * Clears the value of the 'latitude' field.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder clearLatitude() {
            fieldSetFlags()[6] = false;
            return this;
        }

        /**
         * Gets the value of the 'longitude' field.
         * @return The value.
         */
        public double getLongitude() {
            return longitude;
        }


        /**
         * Sets the value of the 'longitude' field.
         * @param value The value of 'longitude'.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder setLongitude(double value) {
            validate(fields()[7], value);
            this.longitude = value;
            fieldSetFlags()[7] = true;
            return this;
        }

        /**
         * Checks whether the 'longitude' field has been set.
         * @return True if the 'longitude' field has been set, false otherwise.
         */
        public boolean hasLongitude() {
            return fieldSetFlags()[7];
        }


        /**
         * Clears the value of the 'longitude' field.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder clearLongitude() {
            fieldSetFlags()[7] = false;
            return this;
        }

        /**
         * Gets the value of the 'dmaCode' field.
         * @return The value.
         */
        public java.lang.CharSequence getDmaCode() {
            return dmaCode;
        }


        /**
         * Sets the value of the 'dmaCode' field.
         * @param value The value of 'dmaCode'.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder setDmaCode(java.lang.CharSequence value) {
            validate(fields()[8], value);
            this.dmaCode = value;
            fieldSetFlags()[8] = true;
            return this;
        }

        /**
         * Checks whether the 'dmaCode' field has been set.
         * @return True if the 'dmaCode' field has been set, false otherwise.
         */
        public boolean hasDmaCode() {
            return fieldSetFlags()[8];
        }


        /**
         * Clears the value of the 'dmaCode' field.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder clearDmaCode() {
            dmaCode = null;
            fieldSetFlags()[8] = false;
            return this;
        }

        /**
         * Gets the value of the 'areaCode' field.
         * @return The value.
         */
        public java.lang.CharSequence getAreaCode() {
            return areaCode;
        }


        /**
         * Sets the value of the 'areaCode' field.
         * @param value The value of 'areaCode'.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder setAreaCode(java.lang.CharSequence value) {
            validate(fields()[9], value);
            this.areaCode = value;
            fieldSetFlags()[9] = true;
            return this;
        }

        /**
         * Checks whether the 'areaCode' field has been set.
         * @return True if the 'areaCode' field has been set, false otherwise.
         */
        public boolean hasAreaCode() {
            return fieldSetFlags()[9];
        }


        /**
         * Clears the value of the 'areaCode' field.
         * @return This builder.
         */
        public GeoIpCityAvroEntity.Builder clearAreaCode() {
            areaCode = null;
            fieldSetFlags()[9] = false;
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public GeoIpCityAvroEntity build() {
            try {
                GeoIpCityAvroEntity record = new GeoIpCityAvroEntity();
                record.startIpNum = fieldSetFlags()[0] ? this.startIpNum : (java.lang.Integer) defaultValue(fields()[0]);
                record.endIpNum = fieldSetFlags()[1] ? this.endIpNum : (java.lang.Integer) defaultValue(fields()[1]);
                record.country = fieldSetFlags()[2] ? this.country : (java.lang.CharSequence) defaultValue(fields()[2]);
                record.region = fieldSetFlags()[3] ? this.region : (java.lang.CharSequence) defaultValue(fields()[3]);
                record.city = fieldSetFlags()[4] ? this.city : (java.lang.CharSequence) defaultValue(fields()[4]);
                record.postalCode = fieldSetFlags()[5] ? this.postalCode : (java.lang.CharSequence) defaultValue(fields()[5]);
                record.latitude = fieldSetFlags()[6] ? this.latitude : (java.lang.Double) defaultValue(fields()[6]);
                record.longitude = fieldSetFlags()[7] ? this.longitude : (java.lang.Double) defaultValue(fields()[7]);
                record.dmaCode = fieldSetFlags()[8] ? this.dmaCode : (java.lang.CharSequence) defaultValue(fields()[8]);
                record.areaCode = fieldSetFlags()[9] ? this.areaCode : (java.lang.CharSequence) defaultValue(fields()[9]);
                return record;
            } catch (org.apache.avro.AvroMissingFieldException e) {
                throw e;
            } catch (java.lang.Exception e) {
                throw new org.apache.avro.AvroRuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumWriter<GeoIpCityAvroEntity>
            WRITER$ = (org.apache.avro.io.DatumWriter<GeoIpCityAvroEntity>)MODEL$.createDatumWriter(SCHEMA$);

    @Override public void writeExternal(java.io.ObjectOutput out)
            throws java.io.IOException {
        WRITER$.write(this, SpecificData.getEncoder(out));
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumReader<GeoIpCityAvroEntity>
            READER$ = (org.apache.avro.io.DatumReader<GeoIpCityAvroEntity>)MODEL$.createDatumReader(SCHEMA$);

    @Override public void readExternal(java.io.ObjectInput in)
            throws java.io.IOException {
        READER$.read(this, SpecificData.getDecoder(in));
    }

    @Override protected boolean hasCustomCoders() { return true; }

    @Override public void customEncode(org.apache.avro.io.Encoder out)
            throws java.io.IOException
    {
        out.writeInt(this.startIpNum);

        out.writeInt(this.endIpNum);

        if (this.country == null) {
            out.writeIndex(1);
            out.writeNull();
        } else {
            out.writeIndex(0);
            out.writeString(this.country);
        }

        if (this.region == null) {
            out.writeIndex(1);
            out.writeNull();
        } else {
            out.writeIndex(0);
            out.writeString(this.region);
        }

        if (this.city == null) {
            out.writeIndex(1);
            out.writeNull();
        } else {
            out.writeIndex(0);
            out.writeString(this.city);
        }

        if (this.postalCode == null) {
            out.writeIndex(1);
            out.writeNull();
        } else {
            out.writeIndex(0);
            out.writeString(this.postalCode);
        }

        out.writeDouble(this.latitude);

        out.writeDouble(this.longitude);

        out.writeString(this.dmaCode);

        out.writeString(this.areaCode);

    }

    @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
            throws java.io.IOException
    {
        org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
        if (fieldOrder == null) {
            this.startIpNum = in.readInt();

            this.endIpNum = in.readInt();

            if (in.readIndex() != 0) {
                in.readNull();
                this.country = null;
            } else {
                this.country = in.readString(this.country instanceof Utf8 ? (Utf8)this.country : null);
            }

            if (in.readIndex() != 0) {
                in.readNull();
                this.region = null;
            } else {
                this.region = in.readString(this.region instanceof Utf8 ? (Utf8)this.region : null);
            }

            if (in.readIndex() != 0) {
                in.readNull();
                this.city = null;
            } else {
                this.city = in.readString(this.city instanceof Utf8 ? (Utf8)this.city : null);
            }

            if (in.readIndex() != 0) {
                in.readNull();
                this.postalCode = null;
            } else {
                this.postalCode = in.readString(this.postalCode instanceof Utf8 ? (Utf8)this.postalCode : null);
            }

            this.latitude = in.readDouble();

            this.longitude = in.readDouble();

            this.dmaCode = in.readString(this.dmaCode instanceof Utf8 ? (Utf8)this.dmaCode : null);

            this.areaCode = in.readString(this.areaCode instanceof Utf8 ? (Utf8)this.areaCode : null);

        } else {
            for (int i = 0; i < 10; i++) {
                switch (fieldOrder[i].pos()) {
                    case 0:
                        this.startIpNum = in.readInt();
                        break;

                    case 1:
                        this.endIpNum = in.readInt();
                        break;

                    case 2:
                        if (in.readIndex() != 0) {
                            in.readNull();
                            this.country = null;
                        } else {
                            this.country = in.readString(this.country instanceof Utf8 ? (Utf8)this.country : null);
                        }
                        break;

                    case 3:
                        if (in.readIndex() != 0) {
                            in.readNull();
                            this.region = null;
                        } else {
                            this.region = in.readString(this.region instanceof Utf8 ? (Utf8)this.region : null);
                        }
                        break;

                    case 4:
                        if (in.readIndex() != 0) {
                            in.readNull();
                            this.city = null;
                        } else {
                            this.city = in.readString(this.city instanceof Utf8 ? (Utf8)this.city : null);
                        }
                        break;

                    case 5:
                        if (in.readIndex() != 0) {
                            in.readNull();
                            this.postalCode = null;
                        } else {
                            this.postalCode = in.readString(this.postalCode instanceof Utf8 ? (Utf8)this.postalCode : null);
                        }
                        break;

                    case 6:
                        this.latitude = in.readDouble();
                        break;

                    case 7:
                        this.longitude = in.readDouble();
                        break;

                    case 8:
                        this.dmaCode = in.readString(this.dmaCode instanceof Utf8 ? (Utf8)this.dmaCode : null);
                        break;

                    case 9:
                        this.areaCode = in.readString(this.areaCode instanceof Utf8 ? (Utf8)this.areaCode : null);
                        break;

                    default:
                        throw new java.io.IOException("Corrupt ResolvingDecoder.");
                }
            }
        }
    }
}











