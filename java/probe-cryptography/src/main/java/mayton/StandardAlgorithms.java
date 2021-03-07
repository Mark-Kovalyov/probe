package mayton;


import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.util.TreeSet;

public class StandardAlgorithms {

    public static void main(String[] args) throws Exception {

        for (Provider provider: Security.getProviders()) {
            System.out.println(provider.getName() + ":");
            for (String key: new TreeSet<>(provider.stringPropertyNames())) {
                System.out.println("  - { \"" + key + "\" : \"" + provider.getProperty(key) + "\" }");
            }
        }


    }

}
