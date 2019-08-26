package mayton.probe.sdb;

import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.sdb.SDBFactory;
import org.apache.jena.sdb.Store;
import org.apache.jena.sdb.StoreDesc;
import org.apache.jena.sdb.sql.SDBConnection;
import org.apache.jena.sdb.store.DatabaseType;
import org.apache.jena.sdb.store.LayoutType;
import org.apache.jena.sdb.store.StoreFormatter;
import org.apache.jena.sdb.store.StoreLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.UUID;

import static org.apache.jena.graph.NodeFactory.createLiteral;
import static org.apache.jena.graph.NodeFactory.createURI;

public class SdbPGLoad {

    static Logger logger = LoggerFactory.getLogger(SdbPGLoad.class);

    public static void main(String[] args) throws Exception {

        StoreDesc storeDesc = new StoreDesc(
                LayoutType.LayoutSimple,
                DatabaseType.PostgreSQL
        );

        String jdbcURL = "jdbc:postgresql://localhost:5432/maytondb";

        Connection conn = DriverManager.getConnection(jdbcURL, "mayton", "mayton123");

        SDBConnection sdbconn = new SDBConnection(conn);
        sdbconn.setLogSQLQueries(true);
        sdbconn.setLogSQLExceptions(true);
        sdbconn.setLogSQLStatements(true);

        Store store = SDBFactory.connectStore(sdbconn, storeDesc) ;

        try{
            StoreFormatter formatter = store.getTableFormatter();
            formatter.create();
        } catch (Throwable ex) {
            logger.warn("Exception during formatter.create", ex);
        }


        StoreLoader loader = store.getLoader();

        loader.addTriple(Triple.create(
                NodeFactory.createURI("user:mayton"),
                NodeFactory.createURI("a"),
                NodeFactory.createURI("user:user")
        ));

        for(int i = 0;i<100;i++) {
            loader.addTriple(
                    Triple.create(
                        createURI("ns:"+ UUID.randomUUID()),
                        createURI("ns:attr"),
                        createLiteral(""+i))
            );
        }

        store.close();

        sdbconn.close();

    }

}
