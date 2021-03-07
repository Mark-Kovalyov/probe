package mayton.http;

/**
 * Public proxy-servers
 */
public enum Proxy {

    POLAND("92.244.36.90", 47150),
    GERMANY("173.212.209.137", 3128);

    public final String ip;
    public final int port;

    Proxy(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

}
