package mayton.semanticweb;

public class FieldDescriptor {

    public String fieldName;
    public String dataType;
    public int maxLength; // chars

    public FieldDescriptor(String fieldName, int maxLength) {
        this.fieldName = fieldName;
        this.dataType = "varchar";
        this.maxLength = maxLength;
    }

    public FieldDescriptor(String fieldName) {
        this.fieldName = fieldName;
        this.dataType = "varchar";
        this.maxLength = 0;
    }

    public String formatExpr() {
        String expr = fieldName + " " +
                dataType + "(" +
                maxLength + ")";
        return expr;
    }

    public void updateMaxLength(int length) {
        maxLength = length > maxLength ? length : maxLength;
    }
}
