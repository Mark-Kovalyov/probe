package mayton.emp;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.HexDump;
import org.apache.commons.io.output.NullOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static java.util.Arrays.asList;

public class MainEmp {

    public static void main(String[] args) throws IOException {
        // 10;ACCOUNTING;NEW YORK
        Debt debt = Debt.newBuilder()
                .setDebtName("ACCOUNTING")
                .setLoc("NEW YORK")
                .setDeptNo(10)
                .addAllEmps(asList(
                        // 7654;MARTIN;SALESMAN;7698;28-SEP-1981;1250;1400;30
                        Emp.newBuilder()
                                .setEmpId(7654)
                                .setEname("Martin")
                                .setJob(Emp.Jobs.SALESMAN)
                                .setMgr(7698)
                                .setHiredate("28-SEP-1981")
                                .setSal(1250.0)
                                .setComm(1400.0)
                                .build()
                )).build();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        debt.writeDelimitedTo(byteArrayOutputStream);

        byteArrayOutputStream.flush();

        HexDump.dump(byteArrayOutputStream.toByteArray(), 0, System.out, 0);

    }

}
