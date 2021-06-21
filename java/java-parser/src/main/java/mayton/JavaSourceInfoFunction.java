package mayton;

import com.github.javaparser.ast.CompilationUnit;
import java.util.function.Function;

public class JavaSourceInfoFunction implements Function<CompilationUnit, JavaSourceInfo> {

    @Override
    public JavaSourceInfo apply(CompilationUnit compilationUnit) {
        JavaSourceInfo javaSourceInfo = new JavaSourceInfo();
        return javaSourceInfo;
    }
}
