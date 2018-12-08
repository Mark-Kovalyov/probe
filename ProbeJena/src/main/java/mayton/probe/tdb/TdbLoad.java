package mayton.probe.tdb;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.tdb.TDBFactory;

public class TdbLoad {

    public static void main(String[] args) {
        //Open TDB Dataset
        String directory = "";
        Dataset dataset = TDBFactory.createDataset(directory);

        //Retrieve Named Graph from Dataset, or use Default Graph.
        String graphURI = "http://example.org/myGraph";
        Model model = dataset.getNamedModel(graphURI);

        //Create RDFS Inference Model, or use other Reasoner e.g. OWL.
        InfModel infModel = ModelFactory.createRDFSModel(model);


        //Perform operations on infModel.

    }

}
