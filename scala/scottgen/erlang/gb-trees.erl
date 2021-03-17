T0 = gb_trees:empty().
T1 = gb_trees:insert(7369, { "SMITH", "CLERK", "7902", "17-DEC-1980", "800", "", "20" }, T0).
T2 = gb_trees:insert(7499, { "ALLEN", "SALESMAN", "7698", "20-FEB-1981", "1600", "300", "30" }, T1).
T3 = gb_trees:insert(7521, { "WARD", "SALESMAN", "7698", "22-FEB-1981", "1250", "500", "30" }, T2).
T4 = gb_trees:insert(7566, { "JONES", "MANAGER", "7839", "02-APR-1981", "2975", "", "20" }, T3).
T5 = gb_trees:insert(7654, { "MARTIN", "SALESMAN", "7698", "28-SEP-1981", "1250", "1400", "30" }, T4).
T6 = gb_trees:insert(7698, { "BLAKE", "MANAGER", "7839", "01-MAY-1981", "2850", "", "30" }, T5).
T7 = gb_trees:insert(7782, { "CLARK", "MANAGER", "7839", "09-JUN-1981", "2450", "", "10" }, T6).
T8 = gb_trees:insert(7788, { "SCOTT", "ANALYST", "7566", "19-APR-1987", "3000", "", "20" }, T7).
T9 = gb_trees:insert(7839, { "KING", "PRESIDENT", "", "17-NOV-1981", "5000", "", "10" }, T8).
T10 = gb_trees:insert(7844, { "TURNER", "SALESMAN", "7698", "08-SEP-1981", "1500", "0", "30" }, T9).
T11 = gb_trees:insert(7876, { "ADAMS", "CLERK", "7788", "23-MAY-1987", "1100", "", "20" }, T10).
T12 = gb_trees:insert(7900, { "JAMES", "CLERK", "7698", "03-DEC-1981", "950", "", "30" }, T11).
T13 = gb_trees:insert(7902, { "FORD", "ANALYST", "7566", "03-DEC-1981", "3000", "", "20" }, T12).
T14 = gb_trees:insert(7934, { "MILLER", "CLERK", "7782", "23-JAN-1982", "1300", "", "10" }, T13).
gb_trees:to_list(T14).