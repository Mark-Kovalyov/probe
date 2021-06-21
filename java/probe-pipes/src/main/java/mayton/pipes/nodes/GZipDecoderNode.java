package mayton.pipes.nodes;

import mayton.pipes.MTNode;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class GZipDecoderNode extends MTNode {

    public GZipDecoderNode(String threadName) {
        super(threadName);
    }

    @Override
    public void run() throws IOException {
        IOUtils.copy(new GZIPInputStream(pipedInputStream), pipedOutputStream);
        pipedOutputStream.close();
    }

}
