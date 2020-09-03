package mayton.compression.trie;

import mayton.compression.GenericTextTransformer;

public abstract class DictionaryTrie implements GenericTextTransformer {

    public static final String IGNORED_SYMBOLS = " ,.!?()[]:;\"\t";

    protected Trie trie = new Trie();

    protected int maxDepth = 0;

}
