package mayton.network;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class Urn {
    private String nid;
    private String nss;
    public Urn(String nid, String nss) {
        this.nid = nid;
        this.nss = nss;
    }
}
