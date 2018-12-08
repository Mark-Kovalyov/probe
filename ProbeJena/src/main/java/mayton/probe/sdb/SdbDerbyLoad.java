package mayton.probe.sdb;

import org.apache.jena.graph.Triple;
import org.apache.jena.sdb.SDBFactory;
import org.apache.jena.sdb.Store;
import org.apache.jena.sdb.StoreDesc;
import org.apache.jena.sdb.sql.SDBConnection;
import org.apache.jena.sdb.store.DatabaseType;
import org.apache.jena.sdb.store.LayoutType;
import org.apache.jena.sdb.store.StoreFormatter;
import org.apache.jena.sdb.store.StoreLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

import static java.lang.System.setProperty;
import static org.apache.jena.graph.NodeFactory.createLiteral;
import static org.apache.jena.graph.NodeFactory.createURI;


/**
 *
 * jdbc:derby:[subsubprotocol:][databaseName][;attribute=value]*
 *
 */
public class SdbDerbyLoad {


    public static void main(String[] args ) throws SQLException {

        setProperty("log4j.configuration", "log4j.properties");

        StoreDesc storeDesc = new StoreDesc(
                LayoutType.LayoutSimple,
                DatabaseType.Derby
        );

        String jdbcURL = "jdbc:derby:sdb/derby/derby;create=true";

        Connection conn = DriverManager.getConnection(jdbcURL);

        SDBConnection sdbconn = new SDBConnection(conn);

        Store store = SDBFactory.connectStore(sdbconn, storeDesc);

        StoreFormatter formatter = store.getTableFormatter();

        formatter.create();
        formatter.addIndexes();

        StoreLoader loader = store.getLoader();

        for (int i = 0; i < 100; i++) {
            loader.addTriple(new Triple(
                    createURI("ns:" + UUID.randomUUID()),
                    createURI("ns:attr"),
                    createLiteral("" + i))
            );
        }

        store.close();
    }

}
