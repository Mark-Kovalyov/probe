package mayton.probe.eclipse.rdf;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class Loader {

        static Logger logger = LoggerFactory.getLogger(Loader.class);

        // sed -i 's/^^xsd:string//g' input.txt
        //
        public static void main(String[] args) throws IOException {

            String industry          = "OpenPermID-bulk-industry-20181223_053443.ttl";
            String organizationsGzip = "OpenPermID-bulk-organization-20181223_053449.ttl.gz";

            logger.info(":: [1] Start");
            InputStream is = new FileInputStream("/db/TR/" + industry);
                /*new GZIPInputStream(
                   new FileInputStream("/db/TR/OpenPermID-bulk-organization-20181223_053449.ttl.gz"));*/

            logger.info(":: [2] Model");

            //Model model = ModelFactory.createDefaultModel();
            Model model = Rio.parse(is, "", RDFFormat.TURTLE);

            logger.info(":: [3] Read ttl");

            //RDFDataMgr.read(model, is, Lang.TURTLE);

            is.close();



            logger.info(":: Finish");

        }
    }



