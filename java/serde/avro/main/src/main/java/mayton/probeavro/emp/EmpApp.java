package mayton.probeavro.emp;

public class EmpApp {

    public static void main(String[] args) {
        Emp king = Emp.newBuilder()
                .setEMPNO(555)
                .setENAME("KING")
                .setDEPTNO(0)
                .setCOMM(100.5).setHIREDATE("16/02/1954")
                .setMGR(null)
                .setJOB("PRESIDENT")
                .setSAL(16000)
                .setDEPTNO(44).build();


    }

}
