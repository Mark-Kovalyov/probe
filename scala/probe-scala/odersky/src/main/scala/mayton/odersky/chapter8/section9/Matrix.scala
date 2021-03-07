package mayton.odersky.chapter8.section9

import scala.annotation.tailrec

object Matrix {

  val vect = Array(2, 3, 5, 7, 11, 13, 17, 19)
  val vecSum = vectorSum(vect)
  println(s"vecSum1 = $vecSum")

  def vectorSum(m: Array[Int]): Int = {
    @tailrec
    def vectorSum(m: Array[Int], i: Int, partialSum: Int): Int = {
      if (i < m.length)
        vectorSum(m, i + 1, partialSum + m(i))
      else
        partialSum
    }
    vectorSum(m, 0, 0)
  }

}
