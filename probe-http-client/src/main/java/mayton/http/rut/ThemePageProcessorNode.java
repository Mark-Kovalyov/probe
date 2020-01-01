package mayton.http.rut;

import mayton.http.ContentProcessorNode;
import org.w3c.dom.Document;

import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class ThemePageProcessorNode extends ContentProcessorNode<RutMagnetObject, Object> {

    @Override
    public Iterable<Object> process(Document xmlDocument) {
        return null;
    }
}
