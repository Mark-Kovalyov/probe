package mayton.parsers;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.Token;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LexerTest {

    //@Throws(IOException::class)
    List<Token> getTokensFromText(String txt) throws IOException {
        ByteArrayInputStream iStream = new ByteArrayInputStream(txt.getBytes(StandardCharsets.UTF_8));
        CharStream cStream = CharStreams.fromStream(iStream);
        CustomUrlLexer lexer = new CustomUrlLexer(cStream);
        ANTLRErrorListener errorListener = new ConsoleErrorListener();
        lexer.addErrorListener(errorListener);
        //tokenStream = new CommonTokenStream(lexer);
        //tokenStream.fill();
        //return tokenStream.tokens;
        return Collections.EMPTY_LIST;
    }

    @Test
    //@Throws(IOException::class)
    void testLexer() throws IOException {
        List<Token> tokens = getTokensFromText("http://user:pwd@localhost:5555/path?par1=val1#anchor1");

        assertTrue(tokens.stream().anyMatch(p -> p.getType() == 1));
        //println("Tokens: ")
//        printTokens(tokens)
    }

}
