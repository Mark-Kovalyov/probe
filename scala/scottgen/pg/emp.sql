create table emp(
  empno integer primary key,
  ename varchar(30),
  job varchar(30),
  mgr integer,
  hiredate date,
  sal decimal,
  comm decimal,
  depno integer);
  
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7369,'SMITH','CLERK', 7902, TIMESTAMP '17-DEC-1980', 800, null, 20);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7499,'ALLEN','SALESMAN', 7698, TIMESTAMP '20-FEB-1981', 1600, 300, 30);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7521,'WARD','SALESMAN', 7698, TIMESTAMP '22-FEB-1981', 1250, 500, 30);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7566,'JONES','MANAGER', 7839, TIMESTAMP '02-APR-1981', 2975, null, 20);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7654,'MARTIN','SALESMAN', 7698, TIMESTAMP '28-SEP-1981', 1250, 1400, 30);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7698,'BLAKE','MANAGER', 7839, TIMESTAMP '01-MAY-1981', 2850, null, 30);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7782,'CLARK','MANAGER', 7839, TIMESTAMP '09-JUN-1981', 2450, null, 10);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7788,'SCOTT','ANALYST', 7566, TIMESTAMP '19-APR-1987', 3000, null, 20);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7839,'KING','PRESIDENT', null, TIMESTAMP '17-NOV-1981', 5000, null, 10);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7844,'TURNER','SALESMAN', 7698, TIMESTAMP '08-SEP-1981', 1500, 0, 30);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7876,'ADAMS','CLERK', 7788, TIMESTAMP '23-MAY-1987', 1100, null, 20);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7900,'JAMES','CLERK', 7698, TIMESTAMP '03-DEC-1981', 950, null, 30);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7902,'FORD','ANALYST', 7566, TIMESTAMP '03-DEC-1981', 3000, null, 20);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(7934,'MILLER','CLERK', 7782, TIMESTAMP '23-JAN-1982', 1300, null, 10);
