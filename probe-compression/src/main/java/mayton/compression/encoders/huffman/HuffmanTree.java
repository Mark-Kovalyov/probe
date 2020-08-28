package mayton.compression.encoders.huffman;

import java.util.Objects;

abstract class HuffmanTree implements Comparable<HuffmanTree>  {

    public final int frequency; // the frequency of this tree

    public HuffmanTree(int freq) { frequency = freq; }

    // compares on the frequency
    public int compareTo(HuffmanTree tree) {
        return frequency - tree.frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HuffmanTree that = (HuffmanTree) o;
        return frequency == that.frequency;
    }

}
