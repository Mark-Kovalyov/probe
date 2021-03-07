package org.example;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProbeJsonCollector {

    static class JsonGeneratorWrapper {

        static Logger logger = LoggerFactory.getLogger(JsonGeneratorWrapper.class);

        private JsonGenerator jGenerator;

        public JsonGeneratorWrapper() {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            JsonFactory jfactory = new JsonFactory();
            try {
                jGenerator = jfactory.createGenerator(stream, JsonEncoding.UTF8);
                jGenerator.writeStartArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public JsonGeneratorWrapper append(String s) {
            try {
                jGenerator.writeString(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }

        public JsonGeneratorWrapper append(JsonGeneratorWrapper wrapper) {
            throw new RuntimeException("Ooops! unable to append another wrapper!");
        }

        @Override
        public String toString() {
            return jGenerator.toString();
        }
    }

    public static void main(String[] args) {

        List<String> list = Arrays.asList("The", "quick", "brown", "fox", "jumps", "over", "lazy", "dog");

        String res = list.stream().collect(
                JsonGeneratorWrapper::new,
                JsonGeneratorWrapper::append,
                JsonGeneratorWrapper::append).toString();

        System.out.println(res);

    }

}
