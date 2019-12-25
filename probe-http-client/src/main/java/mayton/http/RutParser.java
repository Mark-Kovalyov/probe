package mayton.http;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;
import static mayton.http.Utils.println;

public class RutParser {

    static Logger logger = LoggerFactory.getLogger(RutParser.class);

    //
    //   https://rutracker.org/forum/index.php
    // Зарубежное кино
    //   https://rutracker.org/forum/viewforum.php?f=7
    //   Фильмы 91-2000
    //     https://rutracker.org/forum/viewforum.php?f=2221
    //        Topic:
    //        https://rutracker.org/forum/viewtopic.php?t=5474857
    //

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

    public static InputStream cleanXMLData(InputStream htmlInputStream) throws UnsupportedEncodingException {


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

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            //.proxy(ProxySelector.of(new InetSocketAddress(Proxy.POLAND.ip, Proxy.POLAND.port)))
            .build();

    private void routeForums(Document document, String forum) {
        MDC.put("forum", forum);
        routeTopics("");
        MDC.remove("forum");
    }

    private void routeTopics(String topic) {
        MDC.put("topic", topic);
        MDC.remove("topic");
    }

    private void routeLandingPage(Document document) {

    }

    private void sendGET() throws Exception, InterruptedException {

        logger.info("Start");


        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://rutracker.org/forum/index.php"))
                .setHeader("HTTP_USER_AGENT", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:71.0) Gecko/20100101 Firefox/71.0")
                .setHeader("HTTP_ACCEPT", "text/html, application/xhtml+xml, application/xml; q=0.9,*/*; q=0.8")
                .setHeader("HTTP_ACCEPT_ENCODING", "identity") // Indicates the identity function (i.e. no compression, nor modification). This value is always considered as acceptable, even if not present.
                .setHeader("HTTP_REFERER", "https://www.google.com/")
                .setHeader("HTTP_ACCEPT_LANGUAGE", "en-US,en;q=0.5")
                .build();



        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        println("================== Response Headers ===================");
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> {
            println(k + ":" + v);
        });

        println("================== Status Code ===================");
        println(response.statusCode());

        println("================== Response Body ===================");
        println(response.body());

        /*IOUtils.write(
                response.body(),
                new FileOutputStream(System.getProperty("user.dir") + "/src/test/resources/phpForumSample1.html"),
                "windows-1251");*/

        //Document doc = cleanXMLDataFromInputStream(new ByteArrayInputStream(response.body().getBytes()));

        logger.info("End");

    }

    public static void main(String[] args) throws Exception {
        RutParser obj = new RutParser();
        obj.sendGET();
    }

}
