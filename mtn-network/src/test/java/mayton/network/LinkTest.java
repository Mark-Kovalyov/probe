package mayton.network;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkTest {


    // magnet:?dn=JavaScript%20language%20specification.V1.1.pdf
    //         &xt=urn:ed2k:5ae809cea87724d34a3aecbb638631b1
    //         &xt=urn:ed2khash:5ae809cea87724d34a3aecbb638631b1
    //         &xl=306004
    //

    // ed2k://|
    //   file|
    //   JavaScript%20language%20specification.V1.1.pdf|
    //   306004|
    //   5AE809CEA87724D34A3AECBB638631B1|/

    @Test
    public void test() {
        Link link = new Ed2kLink("JavaScript language specification.V1.1.pdf", 306004);
        assertEquals("", link.formatLink());
    }

}
