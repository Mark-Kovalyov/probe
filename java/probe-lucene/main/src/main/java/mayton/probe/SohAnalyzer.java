package mayton.probe;
import org.apache.lucene.analysis.Analyzer;

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
