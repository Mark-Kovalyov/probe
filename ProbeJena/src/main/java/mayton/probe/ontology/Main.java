package mayton.probe.ontology;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

public class Main {

    public static void main(String[] args) {

        String myNS = "http://mayton.org";

        Model model = ModelFactory.createDefaultModel();

        Resource r = model.getResource( myNS + "DigitalCamera" );

        OntClass cls = r.as( OntClass.class );

    }

}
