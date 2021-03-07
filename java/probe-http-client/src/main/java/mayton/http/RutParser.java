package mayton.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.w3c.dom.Document;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

import static mayton.http.Utils.println;

public class RutParser {

    static Logger logger = LoggerFactory.getLogger(RutParser.class);

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

    private void sendGET(String baseUrl) throws Exception, InterruptedException {

        logger.info("Start");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(baseUrl))
                .setHeader("HTTP_USER_AGENT",      "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:71.0) Gecko/20100101 Firefox/71.0")
                .setHeader("HTTP_ACCEPT",          "text/html, application/xhtml+xml, application/xml; q=0.9,*/*; q=0.8")
                // Indicates the identity function (i.e. no compression, nor modification). This value is
                // always considered as acceptable, even if not present.
                .setHeader("HTTP_ACCEPT_ENCODING", "identity")
                .setHeader("HTTP_REFERER",         "https://www.google.com/")
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
        Properties properties = new Properties();
        properties.load(new FileInputStream("sensitive.properties"));
        RutParser obj = new RutParser();
        obj.sendGET(properties.getProperty("baseUrl"));
    }

}
