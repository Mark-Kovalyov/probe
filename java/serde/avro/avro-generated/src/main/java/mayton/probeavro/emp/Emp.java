/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package mayton.probeavro.emp;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** Employee table */
@org.apache.avro.specific.AvroGenerated
public class Emp extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6714032631906981922L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Emp\",\"namespace\":\"mayton.probeavro.emp\",\"doc\":\"Employee table\",\"fields\":[{\"name\":\"EMPNO\",\"type\":\"int\"},{\"name\":\"ENAME\",\"type\":\"string\"},{\"name\":\"JOB\",\"type\":{\"type\":\"enum\",\"name\":\"JOBEnum\",\"symbols\":[\"SALESMAN\",\"PRESIDENT\",\"MANAGER\",\"CLERK\"]}},{\"name\":\"MGR\",\"type\":[\"null\",\"int\"]},{\"name\":\"HIREDATE\",\"type\":\"string\"},{\"name\":\"SAL\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":4,\"scale\":2}},{\"name\":\"COMM\",\"type\":[\"null\",\"double\"]},{\"name\":\"DEPTNO\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();
static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private static final BinaryMessageEncoder<Emp> ENCODER =
      new BinaryMessageEncoder<Emp>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Emp> DECODER =
      new BinaryMessageDecoder<Emp>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Emp> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Emp> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Emp> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Emp>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Emp to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Emp from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Emp instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Emp fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private int EMPNO;
   private java.lang.CharSequence ENAME;
   private mayton.probeavro.emp.JOBEnum JOB;
   private java.lang.Integer MGR;
   private java.lang.CharSequence HIREDATE;
   private java.nio.ByteBuffer SAL;
   private java.lang.Double COMM;
   private int DEPTNO;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Emp() {}

  /**
   * All-args constructor.
   * @param EMPNO The new value for EMPNO
   * @param ENAME The new value for ENAME
   * @param JOB The new value for JOB
   * @param MGR The new value for MGR
   * @param HIREDATE The new value for HIREDATE
   * @param SAL The new value for SAL
   * @param COMM The new value for COMM
   * @param DEPTNO The new value for DEPTNO
   */
  public Emp(java.lang.Integer EMPNO, java.lang.CharSequence ENAME, mayton.probeavro.emp.JOBEnum JOB, java.lang.Integer MGR, java.lang.CharSequence HIREDATE, java.nio.ByteBuffer SAL, java.lang.Double COMM, java.lang.Integer DEPTNO) {
    this.EMPNO = EMPNO;
    this.ENAME = ENAME;
    this.JOB = JOB;
    this.MGR = MGR;
    this.HIREDATE = HIREDATE;
    this.SAL = SAL;
    this.COMM = COMM;
    this.DEPTNO = DEPTNO;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return EMPNO;
    case 1: return ENAME;
    case 2: return JOB;
    case 3: return MGR;
    case 4: return HIREDATE;
    case 5: return SAL;
    case 6: return COMM;
    case 7: return DEPTNO;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: EMPNO = (java.lang.Integer)value$; break;
    case 1: ENAME = (java.lang.CharSequence)value$; break;
    case 2: JOB = (mayton.probeavro.emp.JOBEnum)value$; break;
    case 3: MGR = (java.lang.Integer)value$; break;
    case 4: HIREDATE = (java.lang.CharSequence)value$; break;
    case 5: SAL = (java.nio.ByteBuffer)value$; break;
    case 6: COMM = (java.lang.Double)value$; break;
    case 7: DEPTNO = (java.lang.Integer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'EMPNO' field.
   * @return The value of the 'EMPNO' field.
   */
  public int getEMPNO() {
    return EMPNO;
  }


  /**
   * Sets the value of the 'EMPNO' field.
   * @param value the value to set.
   */
  public void setEMPNO(int value) {
    this.EMPNO = value;
  }

  /**
   * Gets the value of the 'ENAME' field.
   * @return The value of the 'ENAME' field.
   */
  public java.lang.CharSequence getENAME() {
    return ENAME;
  }


  /**
   * Sets the value of the 'ENAME' field.
   * @param value the value to set.
   */
  public void setENAME(java.lang.CharSequence value) {
    this.ENAME = value;
  }

  /**
   * Gets the value of the 'JOB' field.
   * @return The value of the 'JOB' field.
   */
  public mayton.probeavro.emp.JOBEnum getJOB() {
    return JOB;
  }


  /**
   * Sets the value of the 'JOB' field.
   * @param value the value to set.
   */
  public void setJOB(mayton.probeavro.emp.JOBEnum value) {
    this.JOB = value;
  }

  /**
   * Gets the value of the 'MGR' field.
   * @return The value of the 'MGR' field.
   */
  public java.lang.Integer getMGR() {
    return MGR;
  }


  /**
   * Sets the value of the 'MGR' field.
   * @param value the value to set.
   */
  public void setMGR(java.lang.Integer value) {
    this.MGR = value;
  }

  /**
   * Gets the value of the 'HIREDATE' field.
   * @return The value of the 'HIREDATE' field.
   */
  public java.lang.CharSequence getHIREDATE() {
    return HIREDATE;
  }


  /**
   * Sets the value of the 'HIREDATE' field.
   * @param value the value to set.
   */
  public void setHIREDATE(java.lang.CharSequence value) {
    this.HIREDATE = value;
  }

  /**
   * Gets the value of the 'SAL' field.
   * @return The value of the 'SAL' field.
   */
  public java.nio.ByteBuffer getSAL() {
    return SAL;
  }


  /**
   * Sets the value of the 'SAL' field.
   * @param value the value to set.
   */
  public void setSAL(java.nio.ByteBuffer value) {
    this.SAL = value;
  }

  /**
   * Gets the value of the 'COMM' field.
   * @return The value of the 'COMM' field.
   */
  public java.lang.Double getCOMM() {
    return COMM;
  }


  /**
   * Sets the value of the 'COMM' field.
   * @param value the value to set.
   */
  public void setCOMM(java.lang.Double value) {
    this.COMM = value;
  }

  /**
   * Gets the value of the 'DEPTNO' field.
   * @return The value of the 'DEPTNO' field.
   */
  public int getDEPTNO() {
    return DEPTNO;
  }


  /**
   * Sets the value of the 'DEPTNO' field.
   * @param value the value to set.
   */
  public void setDEPTNO(int value) {
    this.DEPTNO = value;
  }

  /**
   * Creates a new Emp RecordBuilder.
   * @return A new Emp RecordBuilder
   */
  public static mayton.probeavro.emp.Emp.Builder newBuilder() {
    return new mayton.probeavro.emp.Emp.Builder();
  }

  /**
   * Creates a new Emp RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Emp RecordBuilder
   */
  public static mayton.probeavro.emp.Emp.Builder newBuilder(mayton.probeavro.emp.Emp.Builder other) {
    if (other == null) {
      return new mayton.probeavro.emp.Emp.Builder();
    } else {
      return new mayton.probeavro.emp.Emp.Builder(other);
    }
  }

  /**
   * Creates a new Emp RecordBuilder by copying an existing Emp instance.
   * @param other The existing instance to copy.
   * @return A new Emp RecordBuilder
   */
  public static mayton.probeavro.emp.Emp.Builder newBuilder(mayton.probeavro.emp.Emp other) {
    if (other == null) {
      return new mayton.probeavro.emp.Emp.Builder();
    } else {
      return new mayton.probeavro.emp.Emp.Builder(other);
    }
  }

  /**
   * RecordBuilder for Emp instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Emp>
    implements org.apache.avro.data.RecordBuilder<Emp> {

    private int EMPNO;
    private java.lang.CharSequence ENAME;
    private mayton.probeavro.emp.JOBEnum JOB;
    private java.lang.Integer MGR;
    private java.lang.CharSequence HIREDATE;
    private java.nio.ByteBuffer SAL;
    private java.lang.Double COMM;
    private int DEPTNO;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(mayton.probeavro.emp.Emp.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.EMPNO)) {
        this.EMPNO = data().deepCopy(fields()[0].schema(), other.EMPNO);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.ENAME)) {
        this.ENAME = data().deepCopy(fields()[1].schema(), other.ENAME);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.JOB)) {
        this.JOB = data().deepCopy(fields()[2].schema(), other.JOB);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.MGR)) {
        this.MGR = data().deepCopy(fields()[3].schema(), other.MGR);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.HIREDATE)) {
        this.HIREDATE = data().deepCopy(fields()[4].schema(), other.HIREDATE);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.SAL)) {
        this.SAL = data().deepCopy(fields()[5].schema(), other.SAL);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.COMM)) {
        this.COMM = data().deepCopy(fields()[6].schema(), other.COMM);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.DEPTNO)) {
        this.DEPTNO = data().deepCopy(fields()[7].schema(), other.DEPTNO);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing Emp instance
     * @param other The existing instance to copy.
     */
    private Builder(mayton.probeavro.emp.Emp other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.EMPNO)) {
        this.EMPNO = data().deepCopy(fields()[0].schema(), other.EMPNO);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ENAME)) {
        this.ENAME = data().deepCopy(fields()[1].schema(), other.ENAME);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.JOB)) {
        this.JOB = data().deepCopy(fields()[2].schema(), other.JOB);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.MGR)) {
        this.MGR = data().deepCopy(fields()[3].schema(), other.MGR);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.HIREDATE)) {
        this.HIREDATE = data().deepCopy(fields()[4].schema(), other.HIREDATE);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.SAL)) {
        this.SAL = data().deepCopy(fields()[5].schema(), other.SAL);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.COMM)) {
        this.COMM = data().deepCopy(fields()[6].schema(), other.COMM);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.DEPTNO)) {
        this.DEPTNO = data().deepCopy(fields()[7].schema(), other.DEPTNO);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'EMPNO' field.
      * @return The value.
      */
    public int getEMPNO() {
      return EMPNO;
    }


    /**
      * Sets the value of the 'EMPNO' field.
      * @param value The value of 'EMPNO'.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder setEMPNO(int value) {
      validate(fields()[0], value);
      this.EMPNO = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'EMPNO' field has been set.
      * @return True if the 'EMPNO' field has been set, false otherwise.
      */
    public boolean hasEMPNO() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'EMPNO' field.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder clearEMPNO() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'ENAME' field.
      * @return The value.
      */
    public java.lang.CharSequence getENAME() {
      return ENAME;
    }


    /**
      * Sets the value of the 'ENAME' field.
      * @param value The value of 'ENAME'.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder setENAME(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.ENAME = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'ENAME' field has been set.
      * @return True if the 'ENAME' field has been set, false otherwise.
      */
    public boolean hasENAME() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'ENAME' field.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder clearENAME() {
      ENAME = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'JOB' field.
      * @return The value.
      */
    public mayton.probeavro.emp.JOBEnum getJOB() {
      return JOB;
    }


    /**
      * Sets the value of the 'JOB' field.
      * @param value The value of 'JOB'.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder setJOB(mayton.probeavro.emp.JOBEnum value) {
      validate(fields()[2], value);
      this.JOB = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'JOB' field has been set.
      * @return True if the 'JOB' field has been set, false otherwise.
      */
    public boolean hasJOB() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'JOB' field.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder clearJOB() {
      JOB = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'MGR' field.
      * @return The value.
      */
    public java.lang.Integer getMGR() {
      return MGR;
    }


    /**
      * Sets the value of the 'MGR' field.
      * @param value The value of 'MGR'.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder setMGR(java.lang.Integer value) {
      validate(fields()[3], value);
      this.MGR = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'MGR' field has been set.
      * @return True if the 'MGR' field has been set, false otherwise.
      */
    public boolean hasMGR() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'MGR' field.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder clearMGR() {
      MGR = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'HIREDATE' field.
      * @return The value.
      */
    public java.lang.CharSequence getHIREDATE() {
      return HIREDATE;
    }


    /**
      * Sets the value of the 'HIREDATE' field.
      * @param value The value of 'HIREDATE'.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder setHIREDATE(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.HIREDATE = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'HIREDATE' field has been set.
      * @return True if the 'HIREDATE' field has been set, false otherwise.
      */
    public boolean hasHIREDATE() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'HIREDATE' field.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder clearHIREDATE() {
      HIREDATE = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'SAL' field.
      * @return The value.
      */
    public java.nio.ByteBuffer getSAL() {
      return SAL;
    }


    /**
      * Sets the value of the 'SAL' field.
      * @param value The value of 'SAL'.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder setSAL(java.nio.ByteBuffer value) {
      validate(fields()[5], value);
      this.SAL = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'SAL' field has been set.
      * @return True if the 'SAL' field has been set, false otherwise.
      */
    public boolean hasSAL() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'SAL' field.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder clearSAL() {
      SAL = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'COMM' field.
      * @return The value.
      */
    public java.lang.Double getCOMM() {
      return COMM;
    }


    /**
      * Sets the value of the 'COMM' field.
      * @param value The value of 'COMM'.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder setCOMM(java.lang.Double value) {
      validate(fields()[6], value);
      this.COMM = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'COMM' field has been set.
      * @return True if the 'COMM' field has been set, false otherwise.
      */
    public boolean hasCOMM() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'COMM' field.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder clearCOMM() {
      COMM = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'DEPTNO' field.
      * @return The value.
      */
    public int getDEPTNO() {
      return DEPTNO;
    }


    /**
      * Sets the value of the 'DEPTNO' field.
      * @param value The value of 'DEPTNO'.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder setDEPTNO(int value) {
      validate(fields()[7], value);
      this.DEPTNO = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'DEPTNO' field has been set.
      * @return True if the 'DEPTNO' field has been set, false otherwise.
      */
    public boolean hasDEPTNO() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'DEPTNO' field.
      * @return This builder.
      */
    public mayton.probeavro.emp.Emp.Builder clearDEPTNO() {
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Emp build() {
      try {
        Emp record = new Emp();
        record.EMPNO = fieldSetFlags()[0] ? this.EMPNO : (java.lang.Integer) defaultValue(fields()[0]);
        record.ENAME = fieldSetFlags()[1] ? this.ENAME : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.JOB = fieldSetFlags()[2] ? this.JOB : (mayton.probeavro.emp.JOBEnum) defaultValue(fields()[2]);
        record.MGR = fieldSetFlags()[3] ? this.MGR : (java.lang.Integer) defaultValue(fields()[3]);
        record.HIREDATE = fieldSetFlags()[4] ? this.HIREDATE : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.SAL = fieldSetFlags()[5] ? this.SAL : (java.nio.ByteBuffer) defaultValue(fields()[5]);
        record.COMM = fieldSetFlags()[6] ? this.COMM : (java.lang.Double) defaultValue(fields()[6]);
        record.DEPTNO = fieldSetFlags()[7] ? this.DEPTNO : (java.lang.Integer) defaultValue(fields()[7]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Emp>
    WRITER$ = (org.apache.avro.io.DatumWriter<Emp>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Emp>
    READER$ = (org.apache.avro.io.DatumReader<Emp>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










