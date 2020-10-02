package org.example;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 *
 * @param <T> String        (the type of input elements to the reduction operation)
 * @param <A> JsonGenerator (the mutable accumulation type of the reduction operation (often hidden as an implementation detail)
 * @param <R> the result type of the reduction operation
 */
public class JsonCollector implements Collector<String, JsonGenerator, JsonGenerator> {

    static Logger logger = LoggerFactory.getLogger(JsonCollector.class);

    private JsonGenerator jsonGenerator;

    public JsonCollector() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JsonFactory jfactory = new JsonFactory();
        try {
            jsonGenerator = jfactory.createGenerator(stream, JsonEncoding.UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier<JsonGenerator> supplier() {
        logger.info("supplier()");
        return () -> jsonGenerator;
    }

    @Override
    public BiConsumer<JsonGenerator, String> accumulator() {
        return new BiConsumer<JsonGenerator, String>() {
            @Override
            public void accept(JsonGenerator jsonGenerator, String s) {
                try {
                    jsonGenerator.writeStringField("name", s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public BinaryOperator<JsonGenerator> combiner() {
        return null;
    }

    // Perform the final transformation from the intermediate accumulation type A to the final result type R.
    @Override
    public Function<JsonGenerator, JsonGenerator> finisher() {

        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        logger.info("characteristics()");
        return new HashSet<>() {{
            add(Characteristics.IDENTITY_FINISH);
        }};
    }
}
