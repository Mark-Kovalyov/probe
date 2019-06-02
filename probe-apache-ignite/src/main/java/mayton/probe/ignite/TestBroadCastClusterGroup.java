package mayton.probe.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;

public class TestBroadCastClusterGroup {

    public static void main(String[] args) {
        try(Ignite ignite = Ignition.start("example-compute.xml")) {

            ClusterGroup clusterGroup = ignite.cluster().forRemotes();
            ignite.compute(clusterGroup).broadcast(() -> System.out.println("Hello remotes"));



        }
    }

}
