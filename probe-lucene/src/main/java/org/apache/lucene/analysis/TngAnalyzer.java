package org.apache.lucene.analysis;

import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.miscellaneous.CapitalizationFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import java.util.Arrays;

public class TngAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        StandardTokenizer src = new StandardTokenizer();
        TokenStream result = new LowerCaseFilter(src);
        result = new LowerCaseFilter(result);
        result = new StopFilter(result, new CharArraySet(Arrays.asList('a','i'), true));
        result = new PorterStemFilter(result);
        result = new CapitalizationFilter(result);
        return new TokenStreamComponents(src, result);
    }
}
