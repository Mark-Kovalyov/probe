package mayton.probe.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.ExecutorConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

public class Custom1 {

    public static void main(String[] args) {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setExecutorConfiguration(new ExecutorConfiguration("myPool").setSize(1));

        DataStorageConfiguration dsCfg = new DataStorageConfiguration();
        DataRegionConfiguration dataRegionConfigurations = new DataRegionConfiguration();
        dataRegionConfigurations.setPersistenceEnabled(true);

        dsCfg.getDefaultDataRegionConfiguration();
        dsCfg.setDefaultDataRegionConfiguration(dataRegionConfigurations);
        cfg.setDataStorageConfiguration(dsCfg);

        /*TcpDiscoverySpi tcpDS = new TcpDiscoverySpi();
        TcpDiscoveryKubernetesIpFinder ipFinder = new TcpDiscoveryKubernetesIpFinder();
        ipFinder.setNamespace("ignite");
        tcpDS = tcpDS.setIpFinder(ipFinder);
        cfg.setDiscoverySpi(tcpDS);*/

        Ignite ignite = Ignition.start(cfg);
    }

}
