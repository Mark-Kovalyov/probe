package mayton.parsers;

import java.util.List;

public class JavaSourceInfo {

    private String packageName;
    private String className;
    private String extendsName;
    private List<String> importsList;
    private List<String> implementsList;
    private boolean poison = false;

    public static JavaSourceInfo POISON_PILLOW = new JavaSourceInfo(true);

    public JavaSourceInfo() {}

    public JavaSourceInfo(boolean poison) {
        this.poison = poison;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getExtendsName() {
        return extendsName;
    }

    public void setExtendsName(String extendsName) {
        this.extendsName = extendsName;
    }

    public List<String> getImportsList() {
        return importsList;
    }

    public void setImportsList(List<String> importsList) {
        this.importsList = importsList;
    }

    public List<String> getImplementsList() {
        return implementsList;
    }

    public void setImplementsList(List<String> implementsList) {
        this.implementsList = implementsList;
    }
}
