package mayton.probe;


import org.apache.jena.graph.*;
import org.apache.jena.mem.GraphMem;
import org.apache.jena.n3.turtle.TurtleReader;
import org.apache.jena.ontology.impl.OntModelImpl;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFReader;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.riot.RDFWriter;
import org.apache.jena.sparql.core.Quad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GraphTripleWriteJsonLd {

    public static void main(String[] args) throws FileNotFoundException {

        Graph graph;

        Model model = ModelFactory.createDefaultModel();

        model.read(new FileInputStream("input/nibelungs.ttl"), null, "TTL");

        RDFDataMgr.write(new FileOutputStream("out/nibelungs-jsonld.jsonld"),
                model, RDFFormat.JSONLD);
        RDFDataMgr.write(new FileOutputStream("out/nibelungs-jsonld-compact.jsonld"),
                model, RDFFormat.JSONLD_COMPACT_FLAT);
        RDFDataMgr.write(new FileOutputStream("out/nibelungs-jsonld-pretty.jsonld"),
                model, RDFFormat.JSONLD_PRETTY);
        RDFDataMgr.write(new FileOutputStream("out/nibelungs-jsonld-expand-flat.jsonld"),
                model, RDFFormat.JSONLD_EXPAND_FLAT);


    }

}
