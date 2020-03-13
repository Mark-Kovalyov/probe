package mayton.probe;

import java.net.MalformedURLException;
import java.net.URL;

import static mayton.probe.EmpHelper.createEmployeesIterable;
import static mayton.probe.EmpHelper.max;
import static mayton.probe.StreamUtils.toStream;

public class Main {

    static String decodeUrl(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<s.length();i++) {
            int c = s.charAt(i);
            if (c == '%') {

            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("magnet:?xt=urn:btih:D39AC143AB78DFD0B28D88BC98367081427D38DF&tr=http%3A%2F%2Fbt4.t-ru.org");

        System.out.println(url);

        Emp reducedEmp = toStream(createEmployeesIterable()).reduce(
                new Emp(0, null, null, 0, null, 0, null, 0),
                (emp1, emp2) -> {
                    int empno1 = emp1.getEmpno();
                    int empno2 = emp2.getEmpno();
                    Integer mgr1 = emp1.getMgr();
                    Integer mgr2 = emp2.getMgr();
                    Integer sal1 = emp1.getSal();
                    Integer sal2 = emp1.getSal();
                    return new Emp(
                            Math.max(emp1.getEmpno(), emp2.getEmpno()),                             // enpno
                            null,                                                                   // ename
                            mgr1 == null ? emp1.getEname() : mgr2 == null ? emp2.getEname() : null, // job
                            0,                                                                      // mgr
                            max(emp1.getHiredate(), emp2.getHiredate()),                            // hiredate
                            sal1 + sal2,
                            null,
                            emp1.getDeptno() + emp2.getDeptno());
                }
        );

        System.out.println("Reduced: " + reducedEmp.toString());


    }

}
