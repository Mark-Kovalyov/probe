object LoopingStruct {


  def generator() : LazyList[Int] = {
    /*for(i <- 1 to 10) {
      yield i
    }*/
    //[error] loopingstructures.scala:6:7: illegal start of statement
    //[error]       yield i
    //[error]       ^
    //[error] one error found
    //[error] (Compile / compileIncremental) Compilation failed
    LazyList.empty
  }

  def main(args : Array[String]) = {

    // Generator based loop
    for(i <- 1 to 10) {
      println(i)
    }

    // ForEach Method
    (0 to 9).foreach(i => println(i))

    // ForEach with Method-reference
    (0 to 9).foreach(println(_))

    // Break
    {
      import scala.util.control.Breaks._
      breakable {                             // breakable block
        for (i <- 0 to 100) {
          println(i)
          if (i == 10)
            break()
          // break out of the loop
        }
      }
    }


  }

}