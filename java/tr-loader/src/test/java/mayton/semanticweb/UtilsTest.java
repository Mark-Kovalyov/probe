package mayton.semanticweb;

import net.jqwik.api.*;
import java.util.Random;

@PropertyDefaults(tries = 300)
public class UtilsTest {

    private Random random = new Random();

    private String[] names = new String[] {"user", "client", "order", "bill"};

    private String[] suffix = new String[] {"id", "number", "position", "amount", "count", "size", "multiplier"};

    @Property
    boolean allDbmsNamesAlwaysUppercased(@ForAll("RandomJavaNames") String name) {
        String dbmsName = Utils.filterSQLWordsAndDashStyle(name);
        return dbmsName.toUpperCase().equals(dbmsName);
    }

    @Provide("RandomJavaNames")
    Arbitrary<String> randomJavaName() {
        return Arbitraries.create(() -> names[random.nextInt(names.length)] +
                suffix[random.nextInt(suffix.length)] + random.nextInt(10));
    }

}
