package mayton.probe.eclipse.rdf;

import org.apache.commons.io.input.CountingInputStream;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class Loader {


    static Logger logger = LoggerFactory.getLogger(Loader.class);

        public static void main(String[] args) throws Throwable {

            String industry          = "OpenPermID-bulk-industry-20181223_053443.ttl";
            String organizationsGzip = "OpenPermID-bulk-organization-20181223_053449.ttl.gz";

            String path = "/db/TR/" + organizationsGzip;

            logger.info(":: [1] Start");

            CountingInputStream cis = new CountingInputStream(new FileInputStream(path));

            InputStream is = new GZIPInputStream(cis);

            CountingInputStream cis2 = new CountingInputStream(is);

            logger.info(":: [2] Model");

            new Thread(() -> {
                Stats stats = new Stats();
                stats.setAmount(new File(path).length());
                while (true) {
                    stats.setPosition(cis.getByteCount());
                    logger.info(stats.formatStats());
                    try {
                        Thread.sleep(3_000);
                    } catch (Exception ex) {
                    }
                    ;
                }
            }).start();


                //Model model = ModelFactory.createDefaultModel();
                Model model = Rio.parse(cis2, "", RDFFormat.TURTLE);


            logger.info(":: [3] Read ttl");

            //RDFDataMgr.read(model, is, Lang.TURTLE);



            is.close();

            logger.info(":: Finish");

        }
    }



