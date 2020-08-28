package mayton.compression.encoders.huffman;

public class HuffmanNode extends HuffmanTree {

    public final HuffmanTree left;
    public final HuffmanTree right;

    public HuffmanNode(HuffmanTree l, HuffmanTree r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}
