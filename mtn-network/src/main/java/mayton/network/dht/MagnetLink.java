package mayton.network.dht;

import com.google.common.net.UrlEscapers;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.Immutable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * <pre>
 *     dn (Display Name) — имя файла.
 *     xl (eXact Length) — размер файла в байтах.
 *     dl (Display Length) — отображаемый размер в байтах. ?????
 *     xt (eXact Topic) — URN, содержащий хеш файла.
 *
 *     as (Acceptable Source) — веб-ссылка на файл в Интернете.
 *     xs (eXact Source) — P2P ссылка. ???
 *     kt (Keyword Topic) — ключевые слова для поиска.
 *     mt (Manifest Topic) — ссылка на метафайл, который содержит список магнетов (MAGMA).
 *
 *     tr (TRacker) — адрес трекера для BitTorrent клиентов.
 * </pre>
 */
public class MagnetLink extends GenericDhtLink {

    private List<String> dn;
    private Optional<Long> dl;
    private Optional<Long> xl;
    private List<Urn> xt;
    private List<URL> as;
    private List<String> xs;
    private List<String> kt;
    private List<URL> mt;
    private List<URL> tr;

    public static MagnetLink parse(@NotNull String magnet) {
        // TODO:
        return new MagnetLink.Builder().build();
    }

    public MagnetLink(List<String> dn, Optional<Long> xl, Optional<Long> dl, List<Urn> xt, List<URL> as, List<String> xs, List<String> kt, List<URL> mt, List<URL> tr) {
        this.xl = xl;
        this.dl = dl;
        this.dn = dn == null ? Collections.EMPTY_LIST : dn;
        this.xt = xt == null ? Collections.EMPTY_LIST : xt;
        this.as = as == null ? Collections.EMPTY_LIST : as;
        this.xs = xs == null ? Collections.EMPTY_LIST : xs;
        this.kt = kt == null ? Collections.EMPTY_LIST : kt;
        this.mt = mt == null ? Collections.EMPTY_LIST : mt;
        this.tr = tr == null ? Collections.EMPTY_LIST : tr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : dn) sb.append("&dn=").append(UrlEscapers.urlFragmentEscaper().escape(s));
        for (Urn urn : xt) sb.append("&xt=").append(urn.toString());
        for (URL url : as) sb.append("&as=").append(url.getRef());
        for (String s : xs) sb.append("&xs=").append(UrlEscapers.urlFragmentEscaper().escape(s));
        for (String s : kt) sb.append("&kt=").append(UrlEscapers.urlFragmentEscaper().escape(s));
        for (URL url : mt) sb.append("&mt=").append(url);
        for (URL url : tr) sb.append("&tr=").append(url);
        if (xl.isPresent()) sb.append("&xl=").append(xl.get());
        if (dl.isPresent()) sb.append("&dl=").append(dl.get());
        return "magnet:?" + sb.substring(1);
    }

    public static class Builder {

        private List<String> dn;
        private Optional<Long> dl = Optional.empty();
        private Optional<Long> xl = Optional.empty();
        private List<Urn> xt;
        private List<URL> as;
        private List<String> xs;
        private List<String> kt;
        private List<URL> mt;
        private List<URL> tr;

        public Builder() {
        }

        public MagnetLink build() {
            return new MagnetLink(dn, xl, dl, xt, as, xs, kt, mt, tr);
        }

        public Builder withDisplayName(String name) {
            if (isNotEmpty(name)) {
                if (dn == null) dn = new ArrayList<>();
                dn.add(name);
            }
            return this;
        }

        public Builder withExactTopic(Urn urn) {
            if (xt == null) xt = new ArrayList<>();
            xt.add(urn);
            return this;
        }

        public Builder withAcceptableSource(URL url) {
            if (as == null) as = new ArrayList<>();
            as.add(url);
            return this;
        }

        public Builder withExactSource(String exactSource) {
            if (isNotEmpty(exactSource)) {
                if (xs == null) xs = new ArrayList<>();
                xs.add(exactSource);
            }
            return this;
        }

        public Builder withKeywordTopic(String keywordTopic) {
            if (isNotEmpty(keywordTopic)) {
                if (kt == null) kt = new ArrayList<>();
                kt.add(keywordTopic);
            }
            return this;
        }

        public Builder withManifestTopic(URL manifestTopicUrl) {
            if (mt == null) mt = new ArrayList<>();
            mt.add(manifestTopicUrl);
            return this;
        }

        public Builder withExactLength(long exactLength) {
            xl = Optional.of(exactLength);
            return this;
        }

        public Builder withDisplayLength(long displayLength) {
            dl = Optional.of(displayLength);
            return this;
        }

        public Builder withTracker(URL tracker) {
            if (tr == null) tr = new ArrayList<>();
            tr.add(tracker);
            return this;
        }

    }

}
