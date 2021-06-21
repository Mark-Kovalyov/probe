package mayton.pipes.nodes;

import mayton.pipes.MTNode;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class InitialCopierNode extends MTNode {

    protected InputStream inputStream;

    public InitialCopierNode(InputStream inputStream) {
        super("initialcopier");
        this.inputStream = inputStream;
    }

    @Override
    public void run() throws IOException {
        IOUtils.copy(inputStream, pipedOutputStream);
        pipedOutputStream.close();
    }
}
