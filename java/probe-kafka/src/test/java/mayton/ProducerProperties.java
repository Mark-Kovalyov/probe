package mayton;

import net.jqwik.api.*;

@PropertyDefaults(tries = 300, afterFailure = AfterFailureMode.PREVIOUS_SEED)
public class ProducerProperties {

    @Property
    @Report(Reporting.GENERATED)
    boolean mainPropertyIsTrue(@ForAll("strings") String arg) {
        return arg.length() == arg.substring(1).length() + 1;
    }

    @Provide("strings")
    Arbitrary<String> strings() {
        return Arbitraries.strings().alpha().ofLength(20);
    }

}
