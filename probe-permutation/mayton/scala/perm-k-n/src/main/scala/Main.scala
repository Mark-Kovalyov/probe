object Main extends App {

  def fact(x : Int) : Long = (1L to x).foldLeft(1L)((a,b) => a * b)

  def perm(k : Int, n : Int) : Int = n! / (n - k)! * k!

  println(s"5! = ${fact(5)}")

  println(sOD

}