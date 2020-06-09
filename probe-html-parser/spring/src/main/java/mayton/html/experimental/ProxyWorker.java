package mayton.html.experimental;

import org.apache.commons.lang3.concurrent.CircuitBreaker;
import org.apache.commons.lang3.concurrent.EventCountCircuitBreaker;

import java.io.IOException;
import java.net.*;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class ProxyWorker implements Runnable {

    private Collection<Integer> mids;

    CircuitBreaker circuitBreaker;

    public ProxyWorker(String proxyHost, int port, Collection<Integer> mids) throws IOException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost , port));
        URLConnection conn = new URL("").openConnection(proxy);
        circuitBreaker = new EventCountCircuitBreaker(0, 0L, TimeUnit.SECONDS);
    }

    @Override
    public void run() {

    }
}
