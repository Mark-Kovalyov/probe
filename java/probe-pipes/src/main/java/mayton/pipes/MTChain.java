package mayton.pipes;

import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.io.output.TeeOutputStream;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;

public class MTChain {

    private InputStream original;

    public List<MTNode> mtNodes = new ArrayList<>();

    public MTChain(InputStream original) {
        this.original = original;
    }

    public void addNode(BiConsumer<PipedInputStream, PipedOutputStream> consumer) {

    }

    public MutablePair<MTChain, MTChain> split() {
        TeeOutputStream teeOutputStream = new TeeOutputStream(new PipedOutputStream(), new PipedOutputStream());
        return new MutablePair<>(this, this);
    }

    public void startNodes() {
        mtNodes.forEach(node -> node.start());
    }

    public void joinNodes() throws InterruptedException {
        for(MTNode mtNode: mtNodes) {
            mtNode.join();
        }
    }

}
