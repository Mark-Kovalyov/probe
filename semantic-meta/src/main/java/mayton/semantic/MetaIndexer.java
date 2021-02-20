package mayton.semantic;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.tdb.TDBFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MetaIndexer {

    public static void write() {
        String directory = "/bigdata/semantic-meta";
        Dataset dataset = TDBFactory.createDataset(directory);

        //Retrieve Named Graph from Dataset, or use Default Graph.
        String graphURI = "http://example.org/myGraph";
        Model model = dataset.getNamedModel(graphURI);

        //Create RDFS Inference Model, or use other Reasoner e.g. OWL.
        InfModel infModel = ModelFactory.createRDFSModel(model);

    }

    public static void main(String[] args) throws IOException {



        MetaVisitor metaVisitor = new MetaVisitor(ModelFactory.createDefaultModel());

        Files.walkFileTree(Path.of("/storage/music/Garbage"), metaVisitor);

        Model model = metaVisitor.getModel();

        RDFDataMgr.write(new FileOutputStream("out/semantic-meta.ttl"),
                model, RDFFormat.TURTLE_PRETTY);

        RDFDataMgr.write(new FileOutputStream("out/semantic-meta.xml"),
                model, RDFFormat.RDFXML);
    }

}
