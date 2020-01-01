package mayton.http;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class CleanUtils {

    private CleanUtils() {}

    public static Document cleanXMLDataFromInputStream(InputStream htmlInputStream) throws IOException, ParserConfigurationException, SAXException {
        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        // TODO: from html to XML
        //return builder.parse(cleanXMLData(htmlInputStream));

        return builder.parse(new ByteArrayInputStream("<?xml version='1.0'?><html></html>".getBytes()));
    }

    @NotNull
    public static InputStream cleanXMLData(@NotNull InputStream htmlInputStream) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setXmlOut(true);
        tidy.setXmlTags(true);
        tidy.setXHTML(true);
        tidy.setQuiet(true);
        tidy.setOutputEncoding("UTF-8");
        tidy.setWraplen(Integer.MAX_VALUE);
        tidy.setSmartIndent(true);
        tidy.setMakeClean(true);
        tidy.setForceOutput(true);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(htmlInputStream, outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

}
