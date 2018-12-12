package mayton.probe.sdb;

import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.sdb.SDBFactory;
import org.apache.jena.sdb.Store;
import org.apache.jena.sdb.StoreDesc;
import org.apache.jena.sdb.sql.JDBC;
import org.apache.jena.sdb.sql.SDBConnection;
import org.apache.jena.sdb.store.DatabaseType;
import org.apache.jena.sdb.store.LayoutType;
import org.apache.jena.sdb.store.StoreFormatter;
import org.apache.jena.sdb.store.StoreLoader;
import org.apache.jena.sdb.util.StoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.LogManager;

import static java.lang.System.setProperty;
import static org.apache.jena.graph.NodeFactory.createLiteral;
import static org.apache.jena.graph.NodeFactory.createURI;

/**
 * Embedded
 *
 *  jdbc:h2:~/test 'test' in the user home directory
 *  jdbc:h2:/data/test 'test' in the directory /data
 *  jdbc:h2:test in the current(!) working directory
 *
 * In-Memory
 *
 *  jdbc:h2:mem:test multiple connections in one process
 *  jdbc:h2:mem: unnamed private; one connection
 *
 * Server Mode
 *
 *  jdbc:h2:tcp://localhost/~/test user home dir
 *  jdbc:h2:tcp://localhost//data/test absolute dir
 *  Server start:java -cp *.jar org.h2.tools.Server
 *
 * Settings
 *
 *  jdbc:h2:..;MODE=MySQL compatibility (or HSQLDB,...)
 *  jdbc:h2:..;TRACE_LEVEL_FILE=3 log to *.trace.db
 */
public class SdbH2Load {

    static Logger logger = LoggerFactory.getLogger(SdbH2Load.class);

    public static void main(String[] args ) throws SQLException {

        setProperty("log4j.configuration","log4j.properties");

        logger.error(":: Start");

        // LayoutTripleNodesHash  - OK
        // LayoutSimple           - OK
        // LayoutTripleNodesIndex - OK

        StoreDesc storeDesc = new StoreDesc(
                LayoutType.LayoutSimple,
                DatabaseType.H2
        );

        logger.info(":: create StoreDesc with layout = {}", storeDesc.getLayout());

        String jdbcURL = "jdbc:h2:file:./sdb/h2/h2;USER=sa;TRACE_LEVEL_FILE=3";

        logger.info(":: gerConnection");

        Connection conn = DriverManager.getConnection(jdbcURL);

        logger.info(":: create SDBConnection");

        SDBConnection sdbconn = new SDBConnection(conn) ;

        logger.info(":: connect Store");

        Store store = SDBFactory.connectStore(sdbconn, storeDesc) ;

        logger.info(":: create schema with Formatter object");

        StoreFormatter formatter = store.getTableFormatter();

        formatter.create();

        StoreLoader loader = store.getLoader();

        loader.startBulkUpdate();

        logger.info(":: load 100 triples");

        for(int i = 0;i<100;i++) {
            loader.addTriple(new Triple(
                            createURI("ns:"+ UUID.randomUUID()),
                            createURI("ns:attr"),
                            createLiteral(""+i))
            );
        }

        loader.finishBulkUpdate();

        store.close();

        logger.info(":: End");

    }

}
