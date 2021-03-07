package mayton.probe.spring.hystrix.springhystrix;

import java.util.Collections;
import java.util.List;

public class Factorization {

    private List<String> integers;

    public Factorization() {
        this.integers = Collections.emptyList();
    }

    public Factorization(List<String> integers) {
        this.integers = integers;
    }

    public List<String> getIntegers() {
        return integers;
    }

    public void setIntegers(List<String> integers) {
        this.integers = integers;
    }
}
