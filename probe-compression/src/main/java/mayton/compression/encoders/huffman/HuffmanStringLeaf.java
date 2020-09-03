package mayton.compression.encoders.huffman;

public class HuffmanStringLeaf extends HuffmanTree {

    public String value; // the character this leaf represents

    public HuffmanStringLeaf(int freq, String value) {
        super(freq);
        this.value = value;
    }

}
