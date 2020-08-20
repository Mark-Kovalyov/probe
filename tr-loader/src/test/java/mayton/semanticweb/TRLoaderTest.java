package mayton.semanticweb;

import org.eclipse.rdf4j.rio.RDFFormat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TRLoaderTest {

    @Test
    public void test() {
        assertEquals(RDFFormat.TURTLE, TRLoader.autoDetectRDFFormat("~/hello.ttl"));
        assertEquals(RDFFormat.RDFXML, TRLoader.autoDetectRDFFormat("~/hello.owl"));
        assertEquals(RDFFormat.RDFXML, TRLoader.autoDetectRDFFormat("~/hello.xml"));
        assertEquals(RDFFormat.JSONLD, TRLoader.autoDetectRDFFormat("~/hello.jsonld"));
        //assertEquals(RDFFormat.NTRIPLES, TRLoader.autoDetectRDFFormat("/dev/mnt/1.ntriple"));
        //assertEquals(RDFFormat.NQUADS, TRLoader.autoDetectRDFFormat("/dev/mnt/1.nquad"));
    }

}
