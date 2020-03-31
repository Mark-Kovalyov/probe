package mayton.scala

object Tut01Currying {

  // Curried function
  def f(x : Int)(y : Int) : Int = x + y

  // With lambda
  def h(x : Int) : Int => Int = (y : Int) => x + y

  // Simple function
  def g(x : Int, y : Int) : Int = x + y

  // Yet another definition with Function1 trait
  val sqr1 = new Function1[Int,Int] {
       override def apply(v1: Int) = v1 * v1
  }

  val sqr2 = new Function2[Int,Int,Int] {
    override def apply(v1: Int, v2: Int) = v1 * v2
  }


  def main(args : Array[String]) : Unit = {
    f(0)(1)
    //scala> :t f(0)(1)
    //Int

    f(1)(2)
    // scala> :t f(0)_
    //Int => Int


    // Convert from simple to curried

    // Check for lambda instance
    println("is instance of any ? " + sqr1.isInstanceOf[Any])

    println("is instance of any ? " + sqr1.isInstanceOf[AnyRef])

    // Syntax sugar of .apply() :

    sqr1.apply(1) // <=>
    sqr1(1)

    sqr2.apply(1,2) // <=>
    sqr2(1,2)


  }

}
