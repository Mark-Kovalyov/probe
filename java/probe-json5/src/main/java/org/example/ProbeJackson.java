package org.example;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 */
public class ProbeJackson {
    public static void main(String[] args) throws IOException {

        JsonFactory jfactory = new JsonFactory();
        JsonParser jParser = jfactory.createParser(new FileReader("sample.json"));

        String parsedName = null;
        Integer parsedAge = null;
        List<String> addresses = new LinkedList<>();

        while (jParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jParser.getCurrentName();
            if ("name".equals(fieldname)) {
                jParser.nextToken();
                parsedName = jParser.getText();
            }

            if ("age".equals(fieldname)) {
                jParser.nextToken();
                parsedAge = jParser.getIntValue();
            }

            if ("address".equals(fieldname)) {
                jParser.nextToken();
                while (jParser.nextToken() != JsonToken.END_ARRAY) {
                    addresses.add(jParser.getText());
                }
            }
        }

        jParser.close();

    }
}
