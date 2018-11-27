package mayton.probe;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDFSyntax;

/**
 * oaj.jena.rdf.model 	    The Jena core. Creating and manipulating RDF graphs.
 *
 * oaj.riot                 Reading and Writing RDF.
 *
 * oaj.jena.datatypes 	    Provides the core interfaces through which datatypes
 *                          are described to Jena. Typed literals
 *
 * oaj.jena.ontology 	    Abstractions and convenience classes for accessing
 *                          and manipulating ontologies represented in RDF. Ontology API
 *
 * oaj.jena.rdf.listeners 	Listening for changes to the statements in a model
 *
 * oaj.jena.reasoner 	    The reasoner subsystem is supports a range of inference
 *                          engines which derive additional information from an RDF model 	Reasoner how-to
 *
 * oaj.jena.shared          Common utility classes
 *
 * oaj.jena.vocabulary      A package containing constant classes with predefined constant
 *                          objects for classes and properties defined in well known vocabularies.
 */
public class Main {

    /**
     * http://www.ldodds.com/foaf/foaf-a-matic.html
     *
     * https://www.w3.org/2009/Talks/0615-qbe/
     *
     */


    public static void main(String[] args) {

        FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());

        String baseUrl = "http://mayton.org/#";

        Model model = FileManager.get().loadModel("input/main.xml", baseUrl, null);

        System.out.println("---------------------- Turtle --------------------------------");

        model.write(System.out, "TURTLE", baseUrl);

        // The language in which to write the model is specified by the lang argument.
        // Predefined values are "RDF/XML", "RDF/XML-ABBREV", "N-TRIPLE", "TURTLE",
        // (and "TTL") and "N3". The default value, represented by null, is "RDF/XML".

        System.out.println("---------------------- RDF/XM --------------------------------");

        model.write(System.out, "RDF/XML", baseUrl);

        String queryString =
                "PREFIX foaf:  <http://xmlns.com/foaf/0.1/>\n" +
                "SELECT ?name\n" +
                "WHERE {\n" +
                "    ?person foaf:name ?name .\n" +
                "}";



        Query query = QueryFactory.create(queryString);

        QueryExecution execution = QueryExecutionFactory.create(query);

        ResultSet resultSet = execution.execSelect();

        while(resultSet.hasNext()) {
            QuerySolution querySolution = resultSet.nextSolution();

            Resource res = querySolution.getResource("res");
            Literal  lit = querySolution.getLiteral("lit");


        }

        execution.close();

    }
}
