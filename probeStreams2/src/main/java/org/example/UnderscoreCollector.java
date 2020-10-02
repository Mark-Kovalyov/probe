package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.github.underscore.U;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnderscoreCollector implements Collector<String, U.Chain<String>, U.Chain<String>> {

    static Logger logger = LoggerFactory.getLogger(ProbeJsonCollector.JsonGeneratorWrapper.class);

    @Override
    public Supplier<U.Chain<String>> supplier() {
        logger.info(":: supplier func");
        return () -> U.chain(Collections.<String>emptyList());
    }

    @Override
    public BiConsumer<U.Chain<String>, String> accumulator() {
        logger.info(":: accumulator func");
        return (stringChain, arg) -> {

            logger.info(":: accumulator value arg = {}", arg);
            stringChain.join(arg);
        };
    }

    @Override
    public BinaryOperator<U.Chain<String>> combiner() {
        logger.info(":: combiner func");
        return new BinaryOperator<U.Chain<String>>() {
            @Override
            public U.Chain<String> apply(U.Chain<String> chain1, U.Chain<String> chain2) {
                logger.info(":: combiner value");
                return chain1.concat(chain2.toList());
            }
        };
    }

    @Override
    public Function<U.Chain<String>, U.Chain<String>> finisher() {
        logger.info(":: finisher func");
        return stringChain -> {
            logger.info(":: finisher value");
            return stringChain;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(new HashSet<>());
    }


}
