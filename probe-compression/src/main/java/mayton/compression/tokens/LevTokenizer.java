package mayton.compression.tokens;

import org.apache.lucene.analysis.util.CharTokenizer;

import java.util.HashSet;
import java.util.Set;

public class LevTokenizer extends CharTokenizer {

    private Set<Character> set = new HashSet<>();

    public LevTokenizer(String tokenChars){
        for(int i=0;i<tokenChars.length();i++) {
            set.add((char)tokenChars.charAt(i));
        }
    }

    @Override
    protected boolean isTokenChar(int c) {
        char symbol = (char)c;
        return !set.contains(symbol);
    }

}

