package mayton.http;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.concurrent.ThreadSafe;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@ThreadSafe
public class Utils {

    private Utils() {}

    public static void println(String arg) {
        System.out.println(arg);
    }

    public static PrintStream printf(String format, Object ...args) {
        return System.out.printf(format, args);
    }

    public static void println(int arg) {
        System.out.println(arg);
    }

    @NotNull
    public static Iterable<Node> iterableFromNodeList(@NotNull NodeList nodeList) {
        int length = nodeList.getLength();
        if (length == 0) return Collections.emptyList();
        List<Node> nodeListRes = new ArrayList<>(length);
        int i = 0;
        while(i < length) {
            nodeListRes.add(nodeList.item(i));
            i++;
        }
        return nodeListRes;
    }

    @NotNull
    public static <T> Stream<T> streamFromIterable(@NotNull Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

}
