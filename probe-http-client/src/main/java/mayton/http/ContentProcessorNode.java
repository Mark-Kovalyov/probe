package mayton.http;

import org.w3c.dom.Document;

import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public abstract class ContentProcessorNode<T, R> {

    protected BlockingQueue<R> blockingQueue;

    public abstract Iterable<R> process(Document xmlDocument);

}
