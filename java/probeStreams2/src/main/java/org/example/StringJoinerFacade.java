package org.example;

import java.util.StringJoiner;

public class StringJoinerFacade {
    private StringJoiner stringJoiner;

    public StringJoinerFacade() {
        this.stringJoiner = new StringJoiner(", ", "{", "}");
    }

    public StringJoinerFacade append(String item) {
        this.stringJoiner.add(item);
        return this;
    }

    public StringJoinerFacade append(StringJoinerFacade stringJoinerFacade) {
        this.stringJoiner.add(stringJoinerFacade.getStringJoiner().toString());
        return this;
    }

    @Override
    public String toString() {
        return stringJoiner.toString();
    }

    public StringJoiner getStringJoiner() {
        return stringJoiner;
    }
}
