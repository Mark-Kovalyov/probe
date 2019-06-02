package mayton.probe.ignite;

import mayton.probe.ignite.entities.Person;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;

import java.util.List;
import java.util.UUID;

public class TestCrossCacheJoin {

    public static void main(String[] args) {

        System.out.println("java.version = " + System.getProperty("java.version"));

        Ignite ignite = Ignition.ignite();

        IgniteCache<AffinityKey<UUID>, Person> cache = ignite.cache("persons");

        SqlFieldsQuery query = new SqlFieldsQuery("select ");

        QueryCursor<List<?>> result = cache.query(query);

    }

}
