# Windows Functions (Postgresql 12)

## Links:

- main https://www.postgresql.org/docs/12/tutorial-window.html
- SELECT syntax https://www.postgresql.org/docs/12/sql-select.html
- Window Function Calls https://www.postgresql.org/docs/12/sql-expressions.html#SYNTAX-WINDOW-FUNCTIONS
- Window Functions table https://www.postgresql.org/docs/12/functions-window.html
- GROUP BY, HAGING https://www.postgresql.org/docs/12/queries-table-expressions.html#QUERIES-GROUP
- GROUPING SETS CUBE, ROLLUP https://www.postgresql.org/docs/12/queries-table-expressions.html#QUERIES-GROUPING-SETS

## Examples:
```
SELECT depname, empno, salary, avg(salary) OVER (PARTITION BY depname) FROM empsalary;

SELECT depname, empno, salary,
       rank() OVER (PARTITION BY depname ORDER BY salary DESC)
FROM empsalary;

SELECT salary, sum(salary) OVER () FROM empsalary;

SELECT salary, sum(salary) OVER (ORDER BY salary) FROM empsalary;

SELECT depname, empno, salary, enroll_date
FROM
  (SELECT depname, empno, salary, enroll_date,
          rank() OVER (PARTITION BY depname ORDER BY salary DESC, empno) AS pos
     FROM empsalary
  ) AS ss
WHERE pos < 3;

SELECT sum(salary) OVER w, avg(salary) OVER w
  FROM empsalary
  WINDOW w AS (PARTITION BY depname ORDER BY salary DESC);
```


