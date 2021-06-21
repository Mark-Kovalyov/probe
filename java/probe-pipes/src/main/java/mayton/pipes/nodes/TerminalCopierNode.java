package mayton.pipes.nodes;

import mayton.pipes.MTNode;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;

public class TerminalCopierNode extends MTNode {

    private OutputStream outputStream;

    public TerminalCopierNode(OutputStream outputStream) {
        super("terminalcopier");
        this.outputStream = outputStream;
    }

    @Override
    public void run() throws IOException {
        IOUtils.copy(pipedInputStream, outputStream);
    }
}
