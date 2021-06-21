package mayton;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.*;
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

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

public class JavaClassFileVisitor extends SimpleFileVisitor<Path> {

    public static Logger logger = LoggerFactory.getLogger(JavaClassFileVisitor.class);

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        String name = dir.getFileName().toString();
        if (name.startsWith(".") || name.equals("test") || name.equals("resources")) {
            logger.warn("Skip {}", name);
            return SKIP_SUBTREE;
        } else {
            return CONTINUE;
        }
    }

    public void parseClassOrInterfaceOrEnum(PackageDeclaration packageDeclaration, StringBuilder sb) {

    }

    public void parsePackage(PackageDeclaration packageDeclaration, StringBuilder sb) {
        logger.info(" package {}", packageDeclaration.getName());
        String packageName = packageDeclaration.getName().asString();
        
    }

    private void parseUnknownNode(Node node, StringBuilder sb) {
        if (node instanceof PackageDeclaration) {
            JavaSourceInfo javaSourceContext = new JavaSourceInfo();
            PackageDeclaration packageDeclaration = (PackageDeclaration) node;
            javaSourceContext.packageName = packageDeclaration.getName().asString();
        } else {
            logger.warn("Ignore node type {}", node.getClass());
        }
    }

    /*
    public void parseUnknownNode(Node parentNode, StringBuilder sb) {
        if (parentNode instanceof Name) {
            Name nameObj = (Name) parentNode;
            //logger.info("1");
        } else if (parentNode instanceof PackageDeclaration) {
            parsePackage((PackageDeclaration) parentNode, sb);
        } else if (parentNode instanceof ImportDeclaration) {
            ImportDeclaration importDeclaration = (ImportDeclaration) parentNode;
            logger.info(" import {}", importDeclaration.getName());
        } else if (parentNode instanceof CompilationUnit) {
            CompilationUnit compilationUnit = (CompilationUnit) parentNode;
            //logger.info("3");
        } else if (parentNode instanceof EnumDeclaration) {
            EnumDeclaration enumDeclaration = (EnumDeclaration) parentNode;
            logger.info(" enum {}", enumDeclaration.getName());
        } else if (parentNode instanceof ClassOrInterfaceDeclaration) {
            ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration) parentNode;
            NodeList<ClassOrInterfaceType> extendedTypes = classOrInterfaceDeclaration.getExtendedTypes();
            NodeList<ClassOrInterfaceType> implementedTypes = classOrInterfaceDeclaration.getImplementedTypes();
                //logger.info("  class name identified {}", classOrInterfaceDeclaration.getName().getIdentifier());
                logger.info("  id {}", classOrInterfaceDeclaration.getName().getId());
                logger.info("  interface = {}", classOrInterfaceDeclaration.isInterface());

                logger.info(" implemeted types {}", implementedTypes
                        .stream()
                        .map(ClassOrInterfaceType::getName)
                        .map(SimpleName::asString)
                        .collect(Collectors.joining(", ","{","}"))
                );

                logger.info(" extended types {}", extendedTypes
                        .stream()
                        .map(ClassOrInterfaceType::getName)
                        .map(SimpleName::asString)
                        .collect(Collectors.joining(", ","{","}"))
                        );

                //implementedTypes.forEach(item -> logger.info(" implemented types {}", item.getName()));
                logger.info("  name = {}", classOrInterfaceDeclaration.getName());

        }else {
            Class clazz = parentNode.getClass();
            logger.warn("Unknown type {}", clazz);
        }
        for(Node node : parentNode.getChildNodes()) {
            parseUnknownNode(node, sb);
        }
    }*/

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String name = file.getFileName().toString();
        if (name.endsWith(".java")) {
            logger.debug("file = {}", file);
            InputStream in = new FileInputStream(file.toFile());
            JavaParser jp = new JavaParser();
            ParseResult<CompilationUnit> cu = jp.parse(in);
            Optional<CompilationUnit> res = cu.getResult();
            if (res.isPresent()) {
                CompilationUnit unit = res.get();
                StringBuilder sb = new StringBuilder();
                parseUnknownNode(unit, sb);
            }
        }
        return CONTINUE;
    }


}
