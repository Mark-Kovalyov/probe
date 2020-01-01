package mayton.http.rut;

import mayton.http.ContentProcessorNode;
import org.w3c.dom.Document;

import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class RutLandingPageProcessorNode extends ContentProcessorNode<RutMagnetObject, Object> {


    @Override
    public Iterable<Object> process(Document xmlDocument) {
        return null;
    }
}
