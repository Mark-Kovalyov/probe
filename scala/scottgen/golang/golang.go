type emp struct{
  empno      int,
  ename      string,
  job        int,
  mgr        int,
  hiredate   string,
  sal        int,
  comm       int,
  deptno     int
} deriving (Show)

empt = [
 emp { empno : 7369, ename : "SMITH", job : "CLERK", mgr : 7902, hiredare : "17-DEC-1980", sal : 800, comm : , deptno : 20 }
 emp { empno : 7499, ename : "ALLEN", job : "SALESMAN", mgr : 7698, hiredare : "20-FEB-1981", sal : 1600, comm : 300, deptno : 30 }
 emp { empno : 7521, ename : "WARD", job : "SALESMAN", mgr : 7698, hiredare : "22-FEB-1981", sal : 1250, comm : 500, deptno : 30 }
 emp { empno : 7566, ename : "JONES", job : "MANAGER", mgr : 7839, hiredare : "02-APR-1981", sal : 2975, comm : , deptno : 20 }
 emp { empno : 7654, ename : "MARTIN", job : "SALESMAN", mgr : 7698, hiredare : "28-SEP-1981", sal : 1250, comm : 1400, deptno : 30 }
 emp { empno : 7698, ename : "BLAKE", job : "MANAGER", mgr : 7839, hiredare : "01-MAY-1981", sal : 2850, comm : , deptno : 30 }
 emp { empno : 7782, ename : "CLARK", job : "MANAGER", mgr : 7839, hiredare : "09-JUN-1981", sal : 2450, comm : , deptno : 10 }
 emp { empno : 7788, ename : "SCOTT", job : "ANALYST", mgr : 7566, hiredare : "19-APR-1987", sal : 3000, comm : , deptno : 20 }
 emp { empno : 7839, ename : "KING", job : "PRESIDENT", mgr : , hiredare : "17-NOV-1981", sal : 5000, comm : , deptno : 10 }
 emp { empno : 7844, ename : "TURNER", job : "SALESMAN", mgr : 7698, hiredare : "08-SEP-1981", sal : 1500, comm : 0, deptno : 30 }
 emp { empno : 7876, ename : "ADAMS", job : "CLERK", mgr : 7788, hiredare : "23-MAY-1987", sal : 1100, comm : , deptno : 20 }
 emp { empno : 7900, ename : "JAMES", job : "CLERK", mgr : 7698, hiredare : "03-DEC-1981", sal : 950, comm : , deptno : 30 }
 emp { empno : 7902, ename : "FORD", job : "ANALYST", mgr : 7566, hiredare : "03-DEC-1981", sal : 3000, comm : , deptno : 20 }
 emp { empno : 7934, ename : "MILLER", job : "CLERK", mgr : 7782, hiredare : "23-JAN-1982", sal : 1300, comm : , deptno : 10 }
]