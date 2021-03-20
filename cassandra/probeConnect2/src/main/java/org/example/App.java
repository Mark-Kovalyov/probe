package org.example;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.metadata.Metadata;

import java.net.InetSocketAddress;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Begin");

        try (CqlSession session = CqlSession.builder().withKeyspace("dhtspace").build()) {

            Metadata metadata = session.getMetadata();
            System.out.printf("Connected session: %s%n", session.getName());

            // We use execute to send a query to Cassandra. This returns a ResultSet, which
            // is essentially a collection of Row objects.
            ResultSet rs = session.execute("select release_version from system.local");
            //  Extract the first row (which is the only one in this case).
            Row row = rs.one();

            // Extract the value of the first (and only) column from the row.
            assert row != null;
            String releaseVersion = row.getString("release_version");
            System.out.printf("Cassandra version is: %s%n", releaseVersion);

            ResultSet rs2 = session.execute("select * from nodes_stats");

            for (Row row1 : rs2) {
                System.out.println(row1.getString("node_id"));
            }

        }

/*        CqlSession session = CqlSession.builder()
                .withApplicationName("dht-observer")
                .withKeyspace("dhtspace")
                .withAuthCredentials("mayton", "pwd123")
                .addContactPoint(new InetSocketAddress("localhost", 9042))
                .build();

        ResultSet res = session.execute("select * from nodes");*/

        System.out.println("End");
    }
}
