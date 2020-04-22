package mayton.network;

import mayton.network.dht.MagnetLink;
import mayton.network.dht.Urn;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class MagnetLinkTest {

    @Test
    public void test() throws MalformedURLException {
        MagnetLink link = new MagnetLink.Builder()
                .withDisplayName("JavaScript language specification.V1.1.pdf")
                .withExactLength(306004)
                .withExactTopic(new Urn("ed2k", "5ae809cea87724d34a3aecbb638631b1"))
                .withExactTopic(new Urn("ed2khash", "5ae809cea87724d34a3aecbb638631b1"))
                .build();

        assertEquals("magnet:?dn=JavaScript%20language%20specification.V1.1.pdf" +
                "&xt=urn:ed2k:5ae809cea87724d34a3aecbb638631b1" +
                "&xt=urn:ed2khash:5ae809cea87724d34a3aecbb638631b1" +
                "&xl=306004", link.toString());
    }

    @Test
    public void test2() throws MalformedURLException {

        MagnetLink link = new MagnetLink.Builder()
                .withDisplayName("")
                .withExactLength(3200)
                .withExactTopic(new Urn("sha1", "YNCKHTQCWBTRNJIV4WNAE52SJUQCZO5C"))
                .withExactTopic(new Urn("sha1", "TXGCZQTH26NL6OUQAJJPFALHG2LTGBC7"))
                .withManifestTopic(new URL("https://en.wikipedia.org/wiki/Magnet_URI_scheme"))
                .withKeywordTopic("magnet")
                .withKeywordTopic("DHT")
                .build();

        assertEquals("magnet:?xt=urn:sha1:YNCKHTQCWBTRNJIV4WNAE52SJUQCZO5C&" +
                "xt=urn:sha1:TXGCZQTH26NL6OUQAJJPFALHG2LTGBC7&" +
                "kt=magnet&" +
                "kt=DHT&" +
                "mt=https://en.wikipedia.org/wiki/Magnet_URI_scheme&" +
                "xl=3200", link.toString());
    }

}
