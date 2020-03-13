package mayton.probe;


import java.util.Arrays;
import java.util.stream.Stream;

public class TestReduce {

    public static void main(String[] args) {

        Stream<String> myStream = Arrays.stream(EmpHelper.source.split("\n"));

        int sumLength = myStream.reduce(
                0,
                (Integer l, String s)   -> l + s.length(),
                (Integer s1, Integer s2) -> s1 + s2);

        System.out.println("Reduces sum = " +sumLength);

    }

}
