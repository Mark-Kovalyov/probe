package mayton.probe.ontology;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 * RDFS 	http://www.w3.org/2000/01/rdf-schema#
 * OWL Full 	http://www.w3.org/2002/07/owl#
 * OWL DL 	http://www.w3.org/TR/owl-features/#term_OWLDL
 * OWL Lite 	http://www.w3.org/TR/owl-features/#term_OWLLite
 */
public class OntologyTest {

    public void test() {
        Model model = null;
        Resource r = model.getResource( "ns" + "DigitalCamera" );
        OntClass cls = r.as( OntClass.class );
    }

}
