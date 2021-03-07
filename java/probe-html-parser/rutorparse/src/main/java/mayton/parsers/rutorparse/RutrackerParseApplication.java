package mayton.parsers.rutorparse;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static java.lang.String.format;

public class RutrackerParseApplication {

    public static void main(String[] args) throws IOException {

        Connection connection = Jsoup.connect("");

        Document document = connection.get();

    }

}
