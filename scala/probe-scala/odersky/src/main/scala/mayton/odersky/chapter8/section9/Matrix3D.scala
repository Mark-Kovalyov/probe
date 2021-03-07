package mayton.odersky.chapter8.section9

import scala.annotation.tailrec

object Matrix3D {

  def matrix3dSumTailrec(matrix: Array[Array[Array[Int]]]): Int = {
    @tailrec
    def matrix3dSumTailrec(matrix: Array[Array[Array[Int]]], i: Int, j: Int, k: Int, partialSum: Int): Int = {
      val ROWS = matrix.length
      val COLUMNS = matrix(0).length
      val SHELVES = 50
      if (i < ROWS && j < COLUMNS && k < SHELVES) {
        matrix3dSumTailrec(matrix, i, j, k + 1, partialSum + matrix(i)(j)(k))
      } else if (k == SHELVES) {
        matrix3dSumTailrec(matrix, i, j + 1, 0, partialSum)
      } else if (j == COLUMNS) {
        matrix3dSumTailrec(matrix, i + 1, 0, 0, partialSum)
      } else {
        partialSum
      }
    }
    matrix3dSumTailrec(matrix, 0, 0, 0, 0)
  }

}
