/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package mayton.geo.generated;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2021-07-07")
public enum Country implements org.apache.thrift.TEnum {
  EN(0),
  FR(1),
  GE(2);

  private final int value;

  private Country(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static Country findByValue(int value) { 
    switch (value) {
      case 0:
        return EN;
      case 1:
        return FR;
      case 2:
        return GE;
      default:
        return null;
    }
  }
}
