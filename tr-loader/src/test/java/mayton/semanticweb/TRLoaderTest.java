package mayton.semanticweb;

import org.eclipse.rdf4j.rio.RDFFormat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TRLoaderTest {

    @Test
    public void testAutoDetectRDFFormat() {
        assertEquals(RDFFormat.TURTLE, TRLoader.autoDetectRDFFormat("~/hello.ttl"));
        assertEquals(RDFFormat.RDFXML, TRLoader.autoDetectRDFFormat("~/hello.owl"));
        assertEquals(RDFFormat.RDFXML, TRLoader.autoDetectRDFFormat("~/hello.xml"));
        assertEquals(RDFFormat.JSONLD, TRLoader.autoDetectRDFFormat("~/hello.jsonld"));
    }

    @Test
    public void trimQuotes() {
        assertEquals(0, Utils.lengthFrontSequenceOf("Hello",'\"'));
        assertEquals(1, Utils.lengthFrontSequenceOf("\"Hello",'\"'));
        assertEquals(0, Utils.lengthBackSequenceOf("Hello",'\"'));
        assertEquals(1, Utils.lengthBackSequenceOf("Hello\"",'\"'));
        assertEquals("2002-11-21T00:00:00Z", Utils.trimAllQuotesLeftAndRight("\"2002-11-21T00:00:00Z\"\"\""));
    }


}
