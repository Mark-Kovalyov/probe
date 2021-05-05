module Scott (
 empt
) where

import Data.Time.Calendar
import Data.Maybe

data Emp = Emp{
  empno      :: Integer,
  ename      :: [Char],
  job        :: [Char],
  mgr        :: Maybe[Integer],
  hiredate   :: Day,
  sal        :: Integer,
  comm       :: Maybe[Integer],
  deptno     :: Integer
} deriving (Show)

empt = [
 Emp { empno = 7369, ename = "SMITH", job = "CLERK", mgr = (Just 7902), hiredate = fromGregorian 1980 12 17, sal = 800, comm = Nothing, deptno = 20 },
 Emp { empno = 7499, ename = "ALLEN", job = "SALESMAN", mgr = (Just 7698), hiredate = fromGregorian 1981 2 20, sal = 1600, comm = (Just 300), deptno = 30 },
 Emp { empno = 7521, ename = "WARD", job = "SALESMAN", mgr = (Just 7698), hiredate = fromGregorian 1981 2 22, sal = 1250, comm = (Just 500), deptno = 30 },
 Emp { empno = 7566, ename = "JONES", job = "MANAGER", mgr = (Just 7839), hiredate = fromGregorian 1981 4 02, sal = 2975, comm = Nothing, deptno = 20 },
 Emp { empno = 7654, ename = "MARTIN", job = "SALESMAN", mgr = (Just 7698), hiredate = fromGregorian 1981 9 28, sal = 1250, comm = (Just 1400), deptno = 30 },
 Emp { empno = 7698, ename = "BLAKE", job = "MANAGER", mgr = (Just 7839), hiredate = fromGregorian 1981 5 01, sal = 2850, comm = Nothing, deptno = 30 },
 Emp { empno = 7782, ename = "CLARK", job = "MANAGER", mgr = (Just 7839), hiredate = fromGregorian 1981 6 09, sal = 2450, comm = Nothing, deptno = 10 },
 Emp { empno = 7788, ename = "SCOTT", job = "ANALYST", mgr = (Just 7566), hiredate = fromGregorian 1987 4 19, sal = 3000, comm = Nothing, deptno = 20 },
 Emp { empno = 7839, ename = "KING", job = "PRESIDENT", mgr = Nothing, hiredate = fromGregorian 1981 11 17, sal = 5000, comm = Nothing, deptno = 10 },
 Emp { empno = 7844, ename = "TURNER", job = "SALESMAN", mgr = (Just 7698), hiredate = fromGregorian 1981 9 08, sal = 1500, comm = (Just 0), deptno = 30 },
 Emp { empno = 7876, ename = "ADAMS", job = "CLERK", mgr = (Just 7788), hiredate = fromGregorian 1987 5 23, sal = 1100, comm = Nothing, deptno = 20 },
 Emp { empno = 7900, ename = "JAMES", job = "CLERK", mgr = (Just 7698), hiredate = fromGregorian 1981 12 03, sal = 950, comm = Nothing, deptno = 30 },
 Emp { empno = 7902, ename = "FORD", job = "ANALYST", mgr = (Just 7566), hiredate = fromGregorian 1981 12 03, sal = 3000, comm = Nothing, deptno = 20 },
 Emp { empno = 7934, ename = "MILLER", job = "CLERK", mgr = (Just 7782), hiredate = fromGregorian 1982 1 23, sal = 1300, comm = Nothing, deptno = 10 }
]