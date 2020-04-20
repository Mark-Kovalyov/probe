package mayton.network;

import com.google.common.net.UrlEscapers;

import java.util.List;

public class MagnetLink extends GenericDhtLink {

    private List<String> dn;
    private List<Urn> xt;
    private long size;

    private MagnetLink() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("magnet:");
        sb.append("?dn").append(UrlEscapers.urlFragmentEscaper().escape(name));
        for (Urn urn : xt) {
            sb.append("?xt=").append(urn.toString());
        }

        //"&xt=urn:ed2k:" + sha1.toLowerCase() +
        //"&xt=urn:ed2khash:" + sha1.toLowerCase() +
        //"&xl=" + size;

        return sb.toString();
    }

    public static class Builder {

        public Builder() {
        }

        public MagnetLink build() {
            return new MagnetLink(name, size, sha1);
        }

        public Builder withSha1(String sha1) {
            this.sha1 = sha1;
            return this;
        }
    }

}
