package org.apache.lucene.analysis;

import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.miscellaneous.CapitalizationFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import static java.util.Arrays.asList;

public class CustomMixinAnalyzerExample extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        StandardTokenizer src = new StandardTokenizer();
        TokenStream result = new LowerCaseFilter(src);
        LowerCaseFilter lowerCaseStream = new LowerCaseFilter(result);
        StopFilter stoppedStream = new StopFilter(lowerCaseStream, new CharArraySet(asList('a', 'i'), true));
        PorterStemFilter porteredStem = new PorterStemFilter(stoppedStream);
        CapitalizationFilter capitalizationResult = new CapitalizationFilter(porteredStem);
        return new TokenStreamComponents(src, capitalizationResult);
    }
}
