import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JTidyTest {

    @Test
    public void test() throws TransformerException, UnsupportedEncodingException {

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

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream htmlInputStream = getClass().getResourceAsStream("phpForumSample1.html");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, "UTF-8");
        Document document = tidy.parseDOM(
                new InputStreamReader(htmlInputStream, "windows-1251"),
                outputStreamWriter
        );

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //Result output = new StreamResult(new File(System.getProperty("user.dir") + "/src/test/resources/phpForumSample1.xml"));
        //Source input = new DOMSource(document);
        //transformer.transform(input, output);

    }

}
