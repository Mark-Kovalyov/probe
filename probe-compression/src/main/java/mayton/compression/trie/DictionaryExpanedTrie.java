package mayton.compression.trie;

import mayton.compression.GenericTextTransformer;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import static java.lang.Math.max;

public class DictionaryExpanedTrie extends DictionaryTrie {

    static Logger logger = LoggerFactory.getLogger(DictionaryExpanedTrie.class);

    private void printIndent(PrintWriter pw, String text, int indent) {
        for(int i = 0 ; i<indent;i++) pw.print(" ");
        pw.print(text);
    }

    private void routeTrie(TrieNode trieNode, PrintWriter pw, int indent) {
        for(Map.Entry<Character, TrieNode> e : trieNode.children.entrySet()){
            printIndent(pw, "(" ,indent);
            pw.print(e.getKey());
            pw.print("\n");
            if (e.getValue().endOfWord) {
                pw.print("*");
            }
            routeTrie(e.getValue(), pw, indent + 1);
            maxDepth = max(maxDepth, indent);
            pw.print(")\n");
        }
    }

    @Override
    public void transform(@NotNull Reader in, @NotNull Writer out, @NotNull Properties properties) throws IOException {
        logger.info("STAT text-2-trie transform");
        BufferedReader bufferedReader = new BufferedReader(in);
        String line;
        int lines = 0;
        while((line = bufferedReader.readLine()) != null) {
            String[] tokens = StringUtils.split(line, IGNORED_SYMBOLS);
            Arrays.stream(tokens)
                    .map(token -> token.toLowerCase())
                    .forEach(token -> {
                        trie.insert(token);
                    });
            lines++;
        }
        PrintWriter pw = new PrintWriter(out);
        routeTrie(trie.getRoot(), pw, 0);
        pw.close();
        logger.info("FINISH transform with maxDepth = {}", maxDepth);
    }

}
