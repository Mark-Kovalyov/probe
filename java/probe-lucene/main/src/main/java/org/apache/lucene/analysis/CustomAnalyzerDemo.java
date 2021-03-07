package org.apache.lucene.analysis;

import org.apache.lucene.analysis.custom.CustomAnalyzer;
import java.io.IOException;

public class CustomAnalyzerDemo {

    public void test() throws IOException {

        Analyzer analyzer = CustomAnalyzer.builder()
                .withTokenizer("standard")
                .addTokenFilter("lowercase")
                .addTokenFilter("stop")
                .addTokenFilter("porterstem")
                .addTokenFilter("capitalization")
                .build();


    }

}
