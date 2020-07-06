package org.apache.lucene.analysis;

import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.en.EnglishMinimalStemFilterFactory;
import org.apache.lucene.analysis.ru.RussianLightStemFilterFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CustomAnalyzerDemo {

    public void test() throws IOException {

        Analyzer analyzer = CustomAnalyzer.builder()
                .withTokenizer("standard")
                .addTokenFilter("lowercase")
                .addTokenFilter("stop")
                .addTokenFilter("porterstem")
                .addTokenFilter("capitalization")
                .addCharFilter()
                .build();


    }

}
