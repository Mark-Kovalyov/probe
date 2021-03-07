package mayton;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.util.TreeSet;

public class BouncyCastleAlgorithms {

    public static void main(String[] args) throws NoSuchProviderException, KeyStoreException, NoSuchAlgorithmException {

        BouncyCastleProvider bcProvider = new BouncyCastleProvider();
        String name = bcProvider.getName();
        Security.removeProvider(name); // remove old instance
        Security.addProvider(bcProvider);

        for (Provider provider: Security.getProviders()) {
            if (provider.getName().equals("BC")) {
                System.out.println(provider.getName() + ":");
                for (String key : new TreeSet<>(provider.stringPropertyNames())) {
                    System.out.println("  - { \"" + key + "\" : \"" + provider.getProperty(key) + "\" }");
                }
            }
        }

    }

}
