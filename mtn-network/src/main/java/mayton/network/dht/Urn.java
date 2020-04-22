package mayton.network.dht;

import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class Urn {

    // <URN> ::= "urn:" <NID> ":" <NSS>
    private String nid;
    private String nss;

    public Urn(@NotNull String nid,@NotNull String nss) {
        this.nid = nid;
        this.nss = nss;
    }

    @Override
    public String toString() {
        return "urn:" + nid + ":" + nss;
    }
}
