package mayton.probe;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class TestCollect {

    public static void main(String[] args) {

        Supplier<Emp> supplier = () -> new Emp();

        BiConsumer<Emp, Emp> consumer = new BiConsumer<Emp, Emp>() {
            @Override
            public void accept(Emp t, Emp u) {
                t.setComm(t.getComm() + u.getComm());
            }
        };

        //StreamUtils.toStream(EmpHelper.createEmployeesIterable()).collect(supplier, consumer,

    }

}
