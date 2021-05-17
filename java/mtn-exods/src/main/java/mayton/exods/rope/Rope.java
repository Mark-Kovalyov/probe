package mayton.exods.rope;

import org.apache.commons.lang3.tuple.Pair;

// https://en.wikipedia.org/wiki/Rope_(data_structure)

public interface Rope extends Iterable<String>, Comparable<Rope> {
    void rebalance();
    // O(log n)
    char index(int i);
    // O(log n)
    Pair<Rope,Rope> split(int i);
    // O(log n) without rebalancing / O(n) worst case
    void concat(Rope that);
    // O(log n) without rebalancing / O(n) worst case
    void insert(String string, int i);
    // O(log n) without rebalancing / O(n) worst case
    void append(String string);
    // O(log n)
    void delete(int i, int j);
    // O(n)
    String build();
}
