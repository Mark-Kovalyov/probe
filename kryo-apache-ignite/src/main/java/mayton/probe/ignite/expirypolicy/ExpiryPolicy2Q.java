package mayton.probe.ignite.expirypolicy;

import org.apache.ignite.configuration.DeploymentMode;

import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;

public class ExpiryPolicy2Q implements ExpiryPolicy {

    @Override
    public Duration getExpiryForCreation() {

        return null;
    }

    @Override
    public Duration getExpiryForAccess() {

        return null;
    }

    @Override
    public Duration getExpiryForUpdate() {

        return null;
    }

}
