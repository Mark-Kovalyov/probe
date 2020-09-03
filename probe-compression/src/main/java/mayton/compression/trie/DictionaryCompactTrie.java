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

public class DictionaryCompactTrie extends DictionaryTrie {

    static Logger logger = LoggerFactory.getLogger(DictionaryCompactTrie.class);

    char startOfNode = '('; // Terminal.STX.code
    char endOfWord = '*';   //Terminal.BELL.code
    char endOfNode = ')';   // Terminal.ETX.code

    private void routeTrie(TrieNode trieNode, PrintWriter pw, int indent) {
        for(Map.Entry<Character, TrieNode> e : trieNode.children.entrySet()){
            pw.print(startOfNode);
            pw.print(e.getKey());
            if (e.getValue().endOfWord) {
                pw.print(endOfWord);
                endOfWords++;
            }
            routeTrie(e.getValue(), pw, indent + 1);
            maxDepth = max(maxDepth, indent);
            pw.print(endOfNode);
        }
    }

    private int nodes = 0;
    private int endOfWords = 0;

    private void processNode(String token) {
        nodes++;
        trie.insert(token);
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
                        processNode(token);
                    });
            lines++;
        }
        PrintWriter pw = new PrintWriter(out);
        routeTrie(trie.getRoot(), pw, 0);
        pw.close();
        logger.info("FINISH transform with maxDepth = {}, nodes = {}, end of words = {}, lines = {}", maxDepth, nodes, endOfWords, lines);
    }


}
