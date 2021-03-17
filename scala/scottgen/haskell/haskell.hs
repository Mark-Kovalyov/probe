module scott (
 emp_tab
) where

import Data.Dates

data Emp = Emp{
  Empno      :: Num,
  Ename      :: [Char],
  Job        :: Num,
  Maybe Mgr  :: Num,
  Hiredate   :: DateTime,
  Sal        :: Num,
  Maybe Comm :: Num,
  Deptno     :: Num
} deriving (Show)

empt = [
 Person 7369 "SMITH" "CLERK" Just 7902 DateTime "17-DEC-1980" 800 Nothing 20
 Person 7499 "ALLEN" "SALESMAN" Just 7698 DateTime "20-FEB-1981" 1600 Just 300 30
 Person 7521 "WARD" "SALESMAN" Just 7698 DateTime "22-FEB-1981" 1250 Just 500 30
 Person 7566 "JONES" "MANAGER" Just 7839 DateTime "02-APR-1981" 2975 Nothing 20
 Person 7654 "MARTIN" "SALESMAN" Just 7698 DateTime "28-SEP-1981" 1250 Just 1400 30
 Person 7698 "BLAKE" "MANAGER" Just 7839 DateTime "01-MAY-1981" 2850 Nothing 30
 Person 7782 "CLARK" "MANAGER" Just 7839 DateTime "09-JUN-1981" 2450 Nothing 10
 Person 7788 "SCOTT" "ANALYST" Just 7566 DateTime "19-APR-1987" 3000 Nothing 20
 Person 7839 "KING" "PRESIDENT" Nothing DateTime "17-NOV-1981" 5000 Nothing 10
 Person 7844 "TURNER" "SALESMAN" Just 7698 DateTime "08-SEP-1981" 1500 Just 0 30
 Person 7876 "ADAMS" "CLERK" Just 7788 DateTime "23-MAY-1987" 1100 Nothing 20
 Person 7900 "JAMES" "CLERK" Just 7698 DateTime "03-DEC-1981" 950 Nothing 30
 Person 7902 "FORD" "ANALYST" Just 7566 DateTime "03-DEC-1981" 3000 Nothing 20
 Person 7934 "MILLER" "CLERK" Just 7782 DateTime "23-JAN-1982" 1300 Nothing 10
]