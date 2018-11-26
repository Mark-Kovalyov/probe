package mayton.probe;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

public class Main {

    /**
     * http://www.ldodds.com/foaf/foaf-a-matic.html
     *
     */


    public static void main(String[] args) {

        FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());

        Model model = FileManager.get().loadModel("input/main.xml", "http://mayton.org", null);

        model.write(System.out, "TURTLE");

        String queryString =
                "PREFIX rdf: <http://address.com/#> \n" +
                "SELECT * FROM { \n" +
                " ?s ?p ?o . \n" +
                "}\n";

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
