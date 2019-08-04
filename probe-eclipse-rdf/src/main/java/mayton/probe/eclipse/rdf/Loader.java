package mayton.probe.eclipse.rdf;

import org.apache.commons.io.input.CountingInputStream;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.locks.LockSupport;
import java.util.zip.GZIPInputStream;

import static mayton.probe.eclipse.rdf.Constants.*;

public class Loader {


    static Logger logger = LoggerFactory.getLogger(Loader.class);

        public static void main(String[] args) throws Throwable {

            logger.info(":: [1] Start");

            CountingInputStream cis = new CountingInputStream(new FileInputStream(SRC_PATH + ASSET_GZIP));

            InputStream is = new GZIPInputStream(cis);

            CountingInputStream cis2 = new CountingInputStream(is);

            logger.info(":: [2] Press any key to start");

            // System.in.read();

            logger.info(":: [2.1] Started");

            new Thread(() -> {
                Stats stats = new Stats();
                stats.setAmount(new File(SRC_PATH + ORGANIZATIONS_GZIP).length());
                while (true) {
                    stats.setPosition(cis.getByteCount());
                    logger.info(stats.formatStats());
                    try {
                        LockSupport.parkNanos(3 * 1000_000_000L);
                    } catch (Exception ex) {
                    };
                }
            }).start();

            Model model = Rio.parse(cis2, "", RDFFormat.TURTLE);

            logger.info(":: [3] Read ttl");

            is.close();

            logger.info(":: Finish");

        }
    }


