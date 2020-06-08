package mayton.html.experimental;

import java.io.IOException;
import java.net.*;
import java.util.Collection;

public class ProxyWorker implements Runnable {

    private Collection<Integer> mids;

    public ProxyWorker(String proxyHost, int port, Collection<Integer> mids) throws IOException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost , port));
        URLConnection conn = new URL("").openConnection(proxy);

    }

    @Override
    public void run() {

    }
}
