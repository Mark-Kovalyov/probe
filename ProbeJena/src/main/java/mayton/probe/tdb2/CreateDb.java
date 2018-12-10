package mayton.probe.tdb2;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.tdb.TDBFactory;

public class CreateDb {

    public static void main(String[] args) {

        //Open (Create) TDB Dataset
        String directory = "/db/tdb2";
        Dataset dataset = TDBFactory.createDataset(directory);

        //Retrieve Named Graph from Dataset, or use Default Graph.
        String graphURI = "http://example.org/myGraph";
        Model model = dataset.getNamedModel(graphURI);

        //Create RDFS Inference Model, or use other Reasoner e.g. OWL.

        InfModel infModel = ModelFactory.createRDFSModel(model);

        //Dataset dataset2 = TDBFactory.assembleDataset("./input/nibelungs.ttl") ;


        //Perform operations on infModel.

    }


}
