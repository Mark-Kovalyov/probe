package mayton.orc;

import org.apache.orc.util.BloomFilter;

public class ApacheOrc {
    public static void main(String[] args) {
        long expectedEntries = 1000;
        BloomFilter bloomFilter = new BloomFilter(expectedEntries);


    }
}
