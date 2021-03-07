package mayton.odersky.chapter8.section9

import scala.annotation.tailrec

object Matrix2D {

  var matrix2d = Array.ofDim[Int](ROWS, COLUMNS)
  var matrix2dSum = 0

  val COLUMNS = 7
  val ROWS = 11

  def matrix2dSumTailrec(m: Array[Array[Int]]): Int = {
    @tailrec
    def matrix2dSumTailrec(matrix: Array[Array[Int]], i: Int, j: Int, partialSum: Int): Int = {
      val ROWS = matrix.length
      val COLUMNS = matrix(0).length
      if (i < ROWS && j < COLUMNS) {
        matrix2dSumTailrec(matrix, i, j + 1, partialSum + matrix(i)(j))
      } else if (j == COLUMNS) {
        matrix2dSumTailrec(matrix, i + 1, 0, partialSum)
      } else {
        partialSum
      }
    }
    matrix2dSumTailrec(m, 0, 0, 0)
  }

  for (i <- 0 until ROWS) {
    for (j <- 0 until COLUMNS) {
      matrix2d(i)(j) = j * 7 + i * 3 + 1
      matrix2dSum = matrix2dSum + matrix2d(i)(j)
    }
  }

  for (i <- 0 until ROWS) {
    for (j <- 0 until COLUMNS) {
      printf(s" ${matrix2d(i)(j)}")
    }
    println()
  }

  printf("matrix2dSum(plain) = %d\n", matrix2dSum)

  printf("matrix2dSum(tailrec) = %d\n", matrix2dSumTailrec(matrix2d))


}
