package mayton.compression.encoders.huffman;

public class HuffmanLeaf extends HuffmanTree {

    public final char value; // the character this leaf represents

    public HuffmanLeaf(int freq, char val) {
        super(freq);
        value = val;
    }
}
