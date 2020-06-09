package mayton.html.impl;

import mayton.html.NameGenerator;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ElvenNameGeneratorImpl implements NameGenerator {

    @Override
    public @NotNull String next() {
        return "";
    }
}
