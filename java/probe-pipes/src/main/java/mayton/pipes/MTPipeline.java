package mayton.pipes;

import mayton.pipes.nodes.InitialCopierNode;
import mayton.pipes.nodes.TerminalCopierNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static mayton.pipes.Constants.BUFFER_SIZE;

public class MTPipeline {

    private InputStream initialInputStream;
    private OutputStream terminalOutputStream;
    private List<MTNode> mtNodes = new ArrayList<>();

    protected MTNode lastNode() {
        return mtNodes.size() > 0 ? mtNodes.get(mtNodes.size() - 1) : null;
    }

    public MTPipeline(InputStream initialInputStream) {
        this.initialInputStream = initialInputStream;
    }

    //    [initialInputStream] -> Node1 -> OS1 | IS2 -> Node2 -> OS2 | IS3 -> Node3 -> [ terminalOutputStream ]
    //
    //
    public void addNode(MTNode mtNode) throws IOException {
        if (mtNodes.isEmpty()) {
            // TODO: First node - dummy copier
            InitialCopierNode cn = new InitialCopierNode(initialInputStream);
            cn.pipedOutputStream.connect(mtNode.pipedInputStream);
            mtNodes.add(cn);
            // TODO: Second node
            mtNode.pipedInputStream = new PipedInputStream(BUFFER_SIZE);
            mtNode.pipedOutputStream = new PipedOutputStream();
            mtNodes.add(mtNode);
        } else {
            // TODO: All nodes belongs first must be connected to previous node like chain
            mtNode.pipedInputStream = new PipedInputStream(BUFFER_SIZE);
            mtNode.pipedOutputStream = new PipedOutputStream();
            lastNode().pipedOutputStream.connect(mtNode.pipedInputStream);
            mtNodes.add(mtNode);
        }

    }

    public void addSplitter() throws IOException {
        // TODO
    }

    // TODO: Peek output stream to last node
    public void addTerminalNode(OutputStream terminalOutputStream) throws IOException {
        mtNodes.add(new TerminalCopierNode(terminalOutputStream));
    }
}
