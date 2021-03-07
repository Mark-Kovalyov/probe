package mayton.probe.eclipse.rdf;

import mayton.lib.SofarTracker;
import org.apache.commons.io.input.CountingInputStream;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.LockSupport;
import java.util.zip.GZIPInputStream;

public class Loader {


    static Logger logger = LoggerFactory.getLogger(Loader.class);

        public static void main(String[] args) throws Throwable {

            Properties props = PropertyUtils.getDefaultProperties();
            String inputFile = props.getProperty("inputFile");
            String dataSetPath = props.getProperty("dataSetPath");

            logger.info(":: [1] Start");

            CountingInputStream cis = new CountingInputStream(new FileInputStream(inputFile));

            InputStream is = new GZIPInputStream(cis);

            CountingInputStream cis2 = new CountingInputStream(is);

            logger.info(":: [2] Press any key to start");

            logger.info(":: [2.1] Started");

            new Thread(() -> {
                SofarTracker sofarTracker = SofarTracker.createFileSizeTracker(new File(inputFile).length());
                while (true) {
                    sofarTracker.update(cis.getByteCount());
                    logger.info(sofarTracker.toString());
                    try {
                        LockSupport.parkNanos(3 * 1000_000_000L);
                    } catch (Exception ex) {
                    }
                }
            }).start();

            Model model = Rio.parse(cis2, "", RDFFormat.TURTLE);

            logger.info(":: [3] Read ttl");

            is.close();

            logger.info(":: Finish");

        }
    }



