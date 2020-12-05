package mayton.cassandra;


import com.datastax.driver.core.*;

import java.util.Iterator;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args){
        PoolingOptions poolingOptions = new PoolingOptions().setMaxConnectionsPerHost(HostDistance.REMOTE,1);

        Cluster cluster = Cluster.builder()
                .addContactPoint("127.0.0.1")
                .withPoolingOptions(poolingOptions)
                .build();

        cluster.init();

        Session session = cluster.connect("scott");

        ResultSet rs = session.execute("select * from emp");

        Iterator<Row> i = rs.iterator();

        while(i.hasNext()){
            Row r = i.next();
            out.println(r.getString("ename"));
        }

        session.close();

        cluster.close();
    }

}
