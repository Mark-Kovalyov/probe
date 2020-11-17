package mayton.odersky.chapter8.section1
import scala.annotation.tailrec

object FunctionAndClosures {

    def vectorSum(m : Array[Int]) : Int = {
      @tailrec
      def vectorSum(m : Array[Int], i : Int, partialSum : Int) : Int = {
        if (i < m.length)
          vectorSum(m, i + 1, partialSum + m(i))
        else
          partialSum
      }
      vectorSum(m, 0, 0)
    }

    def matrix2dSumTailrec(m : Array[Array[Int]]): Int = {
      @tailrec
      def matrix2dSumTailrec(matrix : Array[Array[Int]], i : Int, j : Int, partialSum : Int): Int = {
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

    def matrix3dSumTailrec(matrix : Array[Array[Array[Int]]]): Int = {
      @tailrec
      def matrix3dSumTailrec(matrix : Array[Array[Array[Int]]], i : Int, j : Int, k : Int, partialSum : Int): Int = {
        val ROWS    = matrix.length
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

    def main(args : Array[String]) : Unit = {

      val vect = Array(2,3,5,7,11,13,17,19)
      val vecSum = vectorSum(vect)
      println(s"vecSum1 = $vecSum")

      val COLUMNS = 7
      val ROWS = 11
      var matrix2d = Array.ofDim[Int](ROWS, COLUMNS)
      var matrix2dSum = 0

      for(i <- 0 until ROWS) {
        for(j <- 0 until COLUMNS) {
          matrix2d(i)(j) = j * 7 + i * 3 + 1
          matrix2dSum = matrix2dSum + matrix2d(i)(j)
        }
      }

      for(i <- 0 until  ROWS) {
        for(j <- 0 until  COLUMNS) {
          printf(s" ${matrix2d(i)(j)}")
        }
        println()
      }

      printf("matrix2dSum(plain) = %d\n", matrix2dSum)

      printf("matrix2dSum(tailrec) = %d\n", matrix2dSumTailrec(matrix2d))


    }

  }

