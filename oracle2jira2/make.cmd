call mvn clean package
xcopy /Y .\target\oracle2jira.jar .
java  -jar oracle2jira.jar "scott/tiger@127.0.0.1:1521/XE" "select * from emp order by 1"