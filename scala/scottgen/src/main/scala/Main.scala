import java.io.{FileWriter, PrintWriter, Reader}
import java.nio.file.Files
import java.nio.file.Paths
import org.apache.commons.csv._
import org.apache.commons.csv.CSVFormat.DEFAULT

import scala.collection.mutable
import scala.jdk.CollectionConverters.CollectionHasAsScala
//import scala.jdk.CollectionConverters

import scala.jdk.javaapi.CollectionConverters


object Main extends App {

  def customCsvParser(path : String) = {
    new CSVParser(Files.newBufferedReader(Paths.get(path)),
      CSVFormat.DEFAULT
        .withFirstRecordAsHeader()
        .withIgnoreHeaderCase()
        .withTrim()
        .withDelimiter(';'))
  }

  // type opt struct {
  //    shortnm      byte
  //    longnm, help string
  //    needArg      bool
  //}
  //
  //var basenameOpts = []opt {
  //    opt {
  //        shortnm: 'a',
  //        longnm: "multiple",
  //        needArg: false,
  //        help: "Usage for a",
  //    },
  //    opt {
  //        shortnm: 'b',
  //        longnm: "b-option",
  //        needArg: false,
  //        help: "Usage for b",
  //    },
  //}
  def processGolang(outFileName : String, records : Iterable[CSVRecord]) : Boolean = {
    val haskell : PrintWriter = new PrintWriter(new FileWriter(outFileName))

    haskell.println("type emp struct{\n" +
      "  empno      int,\n" +
      "  ename      string,\n" +
      "  job        int,\n" +
      "  mgr        int,\n" +
      "  hiredate   string,\n" +
      "  sal        int,\n" +
      "  comm       int,\n" +
      "  deptno     int\n" +
      "} deriving (Show)\n")

    haskell.println("empt = [")
    records.foreach(item => {
      val empno    = item.get("EMPNO")
      val ename    = item.get("ENAME")
      val job      = item.get("JOB")
      val mgr      = item.get("MGR")
      val hiredate = item.get("HIREDATE")
      val sal      = item.get("SAL")
      val comm     = item.get("COMM")
      val deptno   = item.get("DEPTNO")

      haskell.printf(" emp { empno : %s, ename : \"%s\", job : \"%s\", mgr : %s, hiredare : \"%s\", sal : %s, comm : %s, deptno : %s }\n",
        empno,
        ename,
        job,
        mgr,
        hiredate,
        sal,
        comm,
        deptno)
    })
    haskell.print("]")
    haskell.close()
    true
  }

  def processPg(outFileName: String, records: Iterable[CSVRecord]) : Boolean = {
    val pgw : PrintWriter = new PrintWriter(new FileWriter(outFileName))

    pgw.println(
      """create table emp(
        |  empno integer primary key,
        |  ename varchar(30),
        |  job varchar(30),
        |  mgr integer,
        |  hiredate date,
        |  sal decimal,
        |  comm decimal,
        |  depno integer);
        |  """.stripMargin)

    records.foreach(item => {
      val empno = item.get("EMPNO")
      val ename = item.get("ENAME")
      val job   = item.get("JOB")
      val mgr   = item.get("MGR")
      val hiredate = item.get("HIREDATE")
      val sal      = item.get("SAL")
      val comm     = item.get("COMM")
      val deptno   = item.get("DEPTNO")

      pgw.printf("insert into emp(empno,ename,job,mgr,hiredate,sal,comm,depno) values(%s,'%s','%s', %s, TIMESTAMP '%s', %s, %s, %s);\n",
        empno,
        ename,
        job,
        if (mgr == "") "null" else mgr,
        hiredate,
        sal,
        if (comm == "") "null" else comm,
        deptno)
    })
    pgw.close()
    true
  }

  def processHaskell(outFileName : String, records : Iterable[CSVRecord]) : Boolean = {
    val haskell : PrintWriter = new PrintWriter(new FileWriter(outFileName))
    haskell.println("module scott (")
    haskell.println(" emp_tab")
    haskell.println(") where\n")
    haskell.println("import Data.Dates\n")
    haskell.println("data Emp = Emp{\n" +
      "  Empno      :: Num,\n" +
      "  Ename      :: [Char],\n" +
      "  Job        :: Num,\n" +
      "  Maybe Mgr  :: Num,\n" +
      "  Hiredate   :: DateTime,\n" +
      "  Sal        :: Num,\n" +
      "  Maybe Comm :: Num,\n" +
      "  Deptno     :: Num\n" +
    "} deriving (Show)\n")

    haskell.println("empt = [")
    records.foreach(item => {
      val empno    = item.get("EMPNO")
      val ename    = item.get("ENAME")
      val job      = item.get("JOB")
      val mgr      = item.get("MGR")
      val hiredate = item.get("HIREDATE")
      val sal      = item.get("SAL")
      val comm     = item.get("COMM")
      val deptno   = item.get("DEPTNO")

      haskell.printf(" Person %s \"%s\" \"%s\" %s %s %s %s %s\n",
        empno,
        ename,
        job,
        if (mgr == "") "Nothing" else "Just " + mgr,
        "DateTime \"" + hiredate + "\"",
        sal,
        if (comm == "") "Nothing" else "Just " + comm,
        deptno)
    })
    haskell.print("]")
    haskell.close()
    true
  }


  def processErlang(outFileName : String, records : Iterable[CSVRecord]) : Boolean = {
    val erlang : PrintWriter = new PrintWriter(new FileWriter(outFileName))
    erlang.println("T0 = gb_trees:empty().")
    var d = 0
    records.foreach(item => {
      val empno    = item.get("EMPNO")
      val ename    = item.get("ENAME")
      val job      = item.get("JOB")
      val mgr      = item.get("MGR")
      val hiredate = item.get("HIREDATE")
      val sal      = item.get("SAL")
      val comm     = item.get("COMM")
      val deptno   = item.get("DEPTNO")

      printf("D%d = dict:store(id%s, \"%s\", D%d).\n", d + 1, empno, ename, d)
      erlang.printf("T%d = gb_trees:insert(%s, { \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\" }, T%d).\n",
        d + 1,
        empno,
        ename,
        job,
        mgr,
        hiredate,
        sal,
        comm,
        deptno,
        d)
      d = d + 1
    })
    erlang.printf("gb_trees:to_list(T%d).", d)
    erlang.close()
    true
  }

  processErlang("erlang/gb-trees.erl", (customCsvParser("csv/scott/emp.csv").getRecords()).asScala)
  processHaskell("haskell/haskell.hs", (customCsvParser("csv/scott/emp.csv").getRecords()).asScala)
  processGolang("golang/golang.go", (customCsvParser("csv/scott/emp.csv").getRecords()).asScala)




  processPg("pg/emp.sql", (customCsvParser("csv/scott/emp.csv").getRecords()).asScala)


}