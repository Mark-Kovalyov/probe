package mayton.network;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkTest {


    // magnet:?dn=JavaScript%20language%20specification.V1.1.pdf
    //         &xt=urn:ed2k:5ae809cea87724d34a3aecbb638631b1
    //         &xt=urn:ed2khash:5ae809cea87724d34a3aecbb638631b1
    //         &xl=306004
    //

    @Test
    public void testMagnet() {
        MagnetLink link = new MagnetLink.Builder()
                .withXl(306004)
                .withDn("JavaScript%20language%20specification.V1.1.pdf")
                .withXt;

        assertEquals("magnet:?dn=JavaScript%20language%20specification.V1.1.pdf" +
                "&xt=urn:ed2k:5ae809cea87724d34a3aecbb638631b1" +
                "&xt=urn:ed2khash:5ae809cea87724d34a3aecbb638631b1" +
                "&xl=306004", link.toString());
    }

    // ed2k://|
    //   file|
    //   JavaScript%20language%20specification.V1.1.pdf|
    //   306004|
    //   5AE809CEA87724D34A3AECBB638631B1|/

    @Test
    public void testEd2k() {
        MagnetLink link = new MagnetLink.Builder("JavaScript language specification.V1.1.pdf", 306004L)
                .withSha1("5AE809CEA87724D34A3AECBB638631B1").build();

        assertEquals("magnet:?dn=JavaScript%20language%20specification.V1.1.pdf" +
                "&xt=urn:ed2k:5ae809cea87724d34a3aecbb638631b1" +
                "&xt=urn:ed2khash:5ae809cea87724d34a3aecbb638631b1" +
                "&xl=306004", link.toString());
    }

}
