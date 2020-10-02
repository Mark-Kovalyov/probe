package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.example.JsonFunctions.quotes;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        probeUnder();
    }

    public static void probeUnder() {
        List<String> list = Arrays.asList("The", "quick", "brown", "fox", "jumps", "over", "lazy", "dog");

        logger.info("list = {}", list);

        String res = list.stream().collect(new UnderscoreCollector()).toString();

        logger.info("res = {}", res);
    }

    public static void probeAll() {

        List<String> list = Arrays.asList("The", "quick", "brown", "fox", "jumps", "over", "lazy", "dog");

        List<String> list2 = Arrays.asList("");

        String result = list.stream().map(x -> "name : " + x).collect(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append).toString();

        String result2 = list.stream()
                .map(x -> Map.entry("name",x))
                .map(entry -> Map.entry(quotes(entry.getKey()),quotes(entry.getValue())))
                .map(entry -> entry.getKey() + " : " + entry.getValue())
                .collect(
                        StringJoinerFacade::new,
                        StringJoinerFacade::append,
                        StringJoinerFacade::append).toString();

        System.out.println(result);

        System.out.println(result2);
    }


}
