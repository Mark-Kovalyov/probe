package mayton.probeavro.emp;

import java.util.Collections;

public class EmpApp {

    public static void main(String[] args) {
        Emp king = Emp.newBuilder()
                .setEMPNO(555)
                .setENAME("KING")
                .setDEPTNO(0)
                .setCOMM(100.5).setHIREDATE("16/02/1954")
                .setMGR(null)
                .setJOB(JOBEnum.SALESMAN)
                .setSAL(16000)
                .setDEPTNO(44).build();

        Debt debt = Debt.newBuilder()
                .setDEBTNO(10)
                .setDEBTNAME("SALES")
                .setLOC("New York")
                .build();

        DebtEmp debtEmp = DebtEmp.newBuilder()
                .setDEBTNO(10)
                .setDEBTNAME("STORE")
                .setLOC("Boston")
                .setEMPS(EMPSRecord.newBuilder()
                        .setENAME("John")
                        .build())
                .build();

        DebtAggregator debtAggregator = DebtAggregator.newBuilder()
                .setDEBTNO(10)
                .setEMPCollection(Collections.emptyList())
                .build();
    }

}
