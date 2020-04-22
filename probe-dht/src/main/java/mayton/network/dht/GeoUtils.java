package mayton.network.dht;

import mayton.network.NetworkUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class GeoUtils {

    private static Logger logger = LogManager.getLogger(GeoUtils.class);

    @NotNull
    public static String decodeCountryCity(@NotNull String ip) {
        logger.trace("ip={}", ip);
        long ipv4 = NetworkUtils.parseIpV4(ip);
        logger.trace("ip(integer)={}", ipv4);
        Optional<GeoRecord> res = GeoDb.getInstance().findFirst(ipv4);
        return res.isPresent() ? res.get().toString() : "Uknown";
    }
}
