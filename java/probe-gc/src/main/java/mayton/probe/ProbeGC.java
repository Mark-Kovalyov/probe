package mayton.probe;


import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProbeGC {
    public static void main(String[] args) {
        //      java.lang:type=GarbageCollector,name=collector's name
        ManagementFactory.getGarbageCollectorMXBeans().stream().forEach(bean -> {
            System.out.printf("name = '%s', objectName = '%s', memoryPoolNames = { %s }\n",
                    bean.getName(),
                    bean.getObjectName(),
                    Arrays.stream(bean.getMemoryPoolNames())
                            .collect(Collectors.joining(",")));
        });
    }
}
