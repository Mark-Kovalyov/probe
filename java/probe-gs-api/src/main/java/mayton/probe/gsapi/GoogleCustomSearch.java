package mayton.probe.gsapi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.apache.http.HttpStatus.SC_OK;


public class GoogleCustomSearch {

    static final String GOOGLE_API_KEY = System.getenv("GOOGLE_API_KEY"); //
    static final String GOOGLE_API_V1_URL = "https://www.googleapis.com/customsearch/v1/siterestrict";

    static Logger logger = LoggerFactory.getLogger(GoogleCustomSearch.class);

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        CloseableHttpResponse response;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            int startResult = 0;
            //uriBuilder.addParameter("siteSearchFilter", "i");
            URI uri = new URIBuilder(GOOGLE_API_V1_URL)
                    .addParameter("key", GOOGLE_API_KEY)
                    .addParameter("cx", "?????")
                    .addParameter("q", "????")
                    .addParameter("fields", "queries,searchInformation,items(link,title)")
                    .addParameter("num", "10")
                    // .addParameter("siteSearchFilter", "i");
                    .addParameter("start", String.valueOf(startResult)).build();
            // .addParameter("fileType","pdf");
            // .addParameter("lr", "lang_en");
            HttpGet request = new HttpGet(uri);
            logger.info("{}", uri.toString());

            // add request headers
            request.addHeader(HttpHeaders.USER_AGENT, "Java JSON application 1.0 (gzip)");
            request.addHeader(HttpHeaders.ACCEPT, "application/json");
            request.addHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");
            request.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "en-US,en;q=0.5");
            response = httpClient.execute(request);


            Arrays.stream(response.getAllHeaders()).forEach(header -> {
                logger.info(":: received header  {} : '{}'", header.getName(), header.getValue());
            });

            logger.info("Http status = {}", response.getStatusLine());
            int code = response.getStatusLine().getStatusCode();
            if (code == SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    logger.info("{}", result);
                } else {
                    logger.warn("entity is null");
                }

            } else {
                logger.warn("Status code is not good, the desc = {}", response.getStatusLine().getReasonPhrase());
            }

        }

    }

}
