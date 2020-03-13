package mayton.probe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;
import static org.apache.commons.lang3.StringUtils.replace;

public class EmpHelper {

    static Logger logger = Logger.getLogger("Logger");

    public static String source =
                    "(7369,'SMITH', 'CLERK',7902,to_date('17-12-1980'),800,NULL,20);\n" +
                    "(7499,'ALLEN', 'SALESMAN',7698,to_date('20-2-1981'),1600,300,30);\n" +
                    "(7521,'WARD',  'SALESMAN',7698,to_date('22-2-1981'),1250,500,30);\n" +
                    "(7566,'JONES', 'MANAGER',7839,to_date('2-4-1981'),2975,NULL,20);\n" +
                    "(7654,'MARTIN','SALESMAN',7698,to_date('28-9-1981'),1250,1400,30);\n" +
                    "(7698,'BLAKE', 'MANAGER',7839,to_date('1-5-1981'),2850,NULL,30);\n" +
                    "(7782,'CLARK', 'MANAGER',7839,to_date('9-6-1981'),2450,NULL,10);\n" +
                    "(7788,'SCOTT', 'ANALYST',7566,to_date('13-JUL-87'),3000,NULL,20);\n" + //  to_date('13-JUL-87','dd-mm-rr')-85
                    "(7839,'KING',  'PRESIDENT',NULL,to_date('17-11-1981'),5000,NULL,10);\n" +
                    "(7844,'TURNER','SALESMAN',7698,to_date('8-9-1981'),1500,0,30);\n" +
                    "(7876,'ADAMS', 'CLERK',7788,to_date('13-JUL-87'),1100,NULL,20);\n" + // to_date('13-JUL-87', 'dd-mm-rr')-51
                    "(7900,'JAMES', 'CLERK',7698,to_date('3-12-1981'),950,NULL,30);\n" +
                    "(7902,'FORD',  'ANALYST',7566,to_date('3-12-1981'),3000,NULL,20);\n" +
                    "(7934,'MILLER','CLERK',7782,to_date('23-1-1982'),1300,NULL,10);";


    static LocalDate max(LocalDate d1, LocalDate d2) {
        if (d1 == null) return d2;
        if (d2 == null) return d1;
        return d1.compareTo(d2) > 0 ? d1 : d2;
    }

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy");

    static String formatEmpDate(LocalDate localDate) {
        return localDate == null ? "[null]" : localDate.format(dateTimeFormatter);
    }

    static LocalDate parseOrNull(String d1) {
        try {
            return LocalDate.parse(d1, dateTimeFormatter);
        } catch (DateTimeParseException ex) {
            logger.warning("Unable to parse " + d1);
            return null;
        }
    }

    static Optional<LocalDate> parseOrEmpty(String d1) {
        try {
            return Optional.of(LocalDate.parse(d1, dateTimeFormatter));
        } catch (DateTimeParseException ex) {
            logger.warning("Unable to parse " + d1);
            return Optional.empty();
        }
    }



    public static Iterable<Emp> createEmployeesIterable() {
        List<Emp> emps = new ArrayList();
        String[] rows = source.split(Pattern.quote("\n"));
        List<String> res = Arrays.asList(rows).stream()
                .map(r -> r.substring(1, r.length() - 2))
                .map(r -> replace(r, "(", "")
                        .replace("to_date", "")
                        .replace(")", "")
                        .replace("\"", "")
                        .replace("'", ""))
                .collect(Collectors.toList());


        res.forEach(r -> {
            String[] tokens = r.split(",");
            String mgr = tokens[3];
            String hiredate = tokens[4];
            String comm = tokens[6];
            Emp emp = new Emp(
                    parseInt(tokens[0]), // empno
                    tokens[1], // ename
                    tokens[2], // job
                    "null".equalsIgnoreCase(mgr) ? null : valueOf(mgr),
                    parseOrNull(hiredate),
                    parseInt(tokens[5]),
                    "null".equalsIgnoreCase(comm) ? null : valueOf(comm),
                    parseInt(tokens[7])
            );
            emps.add(emp);

        });

        return emps;
    }



}
