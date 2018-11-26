package mayton.probe;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class Main {

    /**
     * http://www.ldodds.com/foaf/foaf-a-matic.html
     *
     */


    public static void main(String[] args) {

        FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        Model model = FileManager.get().loadModel("in/main.rdf", "http://mayton.org", null);

        model.write(System.out, "TURTLE");



    }
}
