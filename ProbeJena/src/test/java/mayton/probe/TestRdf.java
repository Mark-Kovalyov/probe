package mayton.probe;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFLanguages;
import org.apache.jena.riot.RDFParser;
import org.apache.jena.riot.system.ErrorHandlerFactory;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

public class TestRdf {

    // Example 1 : Common usage¶
    @Test
    public void test() {
        Model model = ModelFactory.createDefaultModel() ;
        model.read("data.ttl") ;



    }

    // Example 2 : Using the RDFDataMgr
    @Test
    public void test2() {
        // Create a model and read into it from file
// "data.ttl" assumed to be Turtle.
        Model model = RDFDataMgr.loadModel("data.ttl") ;

// Create a dataset and read into it from file
// "data.trig" assumed to be TriG.
        Dataset dataset = RDFDataMgr.loadDataset("data.trig") ;

// Read into an existing Model
        RDFDataMgr.read(model, "data2.ttl") ;
    }

    @Test
    public void test3 () throws Exception {
        // The parsers will do the necessary character set conversion.
        try (InputStream in = new FileInputStream("data.some.unusual.extension")) {

            Dataset noWhere = null;

            RDFParser.create()
                    .source(in)
                    .lang(RDFLanguages.TRIG)
                    .errorHandler(ErrorHandlerFactory.errorHandlerStrict)
                    .base("http://example/base")
                    .parse(noWhere);
        }
    }


}

