package mayton.parsers;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.runtime.atn.ATN;

import java.io.StringReader;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        CharStream input = new UnbufferedCharStream(new StringReader("http://user:pwd@localhost:5555/path?par1=val1#anchor1\n"));
        CustomUrlLexer lexer = new CustomUrlLexer(input);
        TokenStream tokenStream = new BufferedTokenStream(lexer);
        Parser parser = new CustomUrlParser(tokenStream);

        System.out.printf("BuildParseTree? = %s", parser.getBuildParseTree());
        TokenStream tokenStream2 = parser.getTokenStream();

    }

}
