package mayton.probe;

import java.io.Reader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;

public class SohAnalyzer extends Analyzer {
    
    private String nonTokenChars = "|=\u0001";
    
    public SohAnalyzer() {
    }
    
    public SohAnalyzer(String nonTokenChars) {
        this.nonTokenChars = nonTokenChars;
    }
    
    @Override
    protected TokenStreamComponents createComponents(final String fieldName) {
      return new TokenStreamComponents(new SohTokenizer(nonTokenChars));
    }

}
