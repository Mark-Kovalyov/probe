package mayton.network;

import com.google.common.net.UrlEscapers;

public class Ed2kLink extends GenericDhtLink {

    private Ed2kLink(String name, long size, String sha1) {
        this.name = name;
        this.size = size;
        this.sha1 = sha1;
    }

    @Override
    public String toString() {
        return "magnet:?dn=" + UrlEscapers.urlFragmentEscaper().escape(name) +
                "&xt=urn:ed2k:" + sha1.toLowerCase() +
                "&xt=urn:ed2khash:" + sha1.toLowerCase() +
                "&xl=" + size;

    }

    public static class Builder {
        private final String name;
        private final long size;
        private String sha1;

        public Builder(String name, long size) {
            this.name = name;
            this.size = size;
        }

        public Ed2kLink build() {
            return new Ed2kLink(name, size, sha1);
        }

        public Ed2kLink.Builder withSha1(String sha1) {
            this.sha1 = sha1;
            return this;
        }
    }
}
