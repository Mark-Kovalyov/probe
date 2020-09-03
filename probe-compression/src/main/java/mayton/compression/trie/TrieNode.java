package mayton.compression.trie;

import java.util.Map;
import java.util.TreeMap;

public class TrieNode {

    public final Map<Character, TrieNode> children = new TreeMap<>();
    public boolean endOfWord;

}
