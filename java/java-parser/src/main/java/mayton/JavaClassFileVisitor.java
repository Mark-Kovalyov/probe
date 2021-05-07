package mayton;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

public class JavaClassFileVisitor extends SimpleFileVisitor<Path> {

    public static Logger logger = LoggerFactory.getLogger(JavaClassFileVisitor.class);

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        logger.debug("file = {}", file);
        InputStream in = new FileInputStream(file.toFile());
        JavaParser jp = new JavaParser();
        ParseResult<CompilationUnit> cu = jp.parse(in);
        Optional<CompilationUnit> res = cu.getResult();
        if (res.isPresent()) {
            logger.debug("present");
        }
        return FileVisitResult.CONTINUE;
    }
}
