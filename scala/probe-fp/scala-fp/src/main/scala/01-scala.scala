import scala.annotation.tailrec

object Main {

// Fibonacci unlimited

def fibo(a : Int, b : Int) : LazyList[Int] = a #:: fibo(b, a + b)

// FIbinacci till MAX int

val GOLDEN_RATIO = 1.0 / ((1.0 + Math.sqrt(5.0)) / 2.0)

def fibo2(a : Int, b : Int) : LazyList[Int] = a #:: (if (a < Int.MaxValue / 3) fibo2(b, a + b) else LazyList.empty)

def fibo3(a : Long, b : Long) : LazyList[Long] = a #:: (if (a < Long.MaxValue / 3) fibo3(b, a + b) else LazyList.empty)

/*
def fibo4(a : AnyVal, b : AnyVal) : LazyList[AnyVal] = {
  a #:: fibo4(b, a + b)
}
*/

  // Factorial

def fact(a : Int) : Int = {
  var prod = 1
  for(x <- 1 to a) prod = prod * x
  prod
}

def fact(x: BigInt): BigInt = {

  @tailrec
  def facttail(x: BigInt, accum: BigInt): BigInt =
    if (x == 0)
      accum
    else
      facttail(x - 1, accum * x)

  facttail(x, 1)
}



  def main(args : Array[String]) : Unit = {
    fibo3(1,1).foreach(item => println(item))
  }

}