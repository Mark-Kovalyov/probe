package mayton;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class Main {

    public static void main(String[] args) throws Exception {
        EmbeddedCacheManager manager = new DefaultCacheManager("my-config-file.xml");
        Cache defaultCache   = manager.getCache();
        Cache someNamedCache = manager.getCache("someNamedCache");

        Configuration c = new ConfigurationBuilder()
                .clustering()
                .cacheMode(CacheMode.REPL_SYNC)
                .build();

        EmbeddedCacheManager manager2 = new DefaultCacheManager("infinispan-config-file.xml");

        Configuration dcc = manager.getDefaultCacheConfiguration();

        Configuration c2 = new ConfigurationBuilder()
                .read(dcc)
                .clustering()
                .cacheMode(CacheMode.DIST_SYNC)
                .l1()
                .lifespan(60000L).build();
    }

}
