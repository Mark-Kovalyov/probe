package mayton.exods;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Iterator;

public class SimpleRope implements Rope {

    private RopeNode root;

    @Override
    public void rebalance() {

    }

    @Override
    public char index(int i) {
        return 0;
    }

    @Override
    public Pair<Rope, Rope> split(int i) {
        return null;
    }

    @Override
    public void concat(Rope that) {

    }

    @Override
    public void insert(String string, int i) {

    }

    @Override
    public void append(String string) {

    }

    @Override
    public void delete(int i, int j) {

    }

    @Override
    public String build() {
        return null;
    }

    @Override
    public int compareTo(Rope strings) {
        return 0;
    }

    @Override
    public Iterator<String> iterator() {
        return null;
    }
}
