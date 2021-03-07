-- def matrix2dSumTailrec(m : Array[Array[Integer]]): Integer = {
   --      @tailrec
   --      def matrix2dSumTailrec(matrix : Array[Array[Integer]], i : Integer, j : Integer, partialSum : Integer): Integer = {
   --        val ROWS = matrix.length
   --        val COLUMNS = matrix(0).length
   --        if (i < ROWS && j < COLUMNS) {
   --          matrix2dSumTailrec(matrix, i, j + 1, partialSum + matrix(i)(j))
   --        } else if (j == COLUMNS) {
   --          matrix2dSumTailrec(matrix, i + 1, 0, partialSum)
   --        } else {
   --          partialSum
   --        }
   --      }
   --      matrix2dSumTailrec(m, 0, 0, 0)
   --    }

:{
import Data.List

rows = 2
columns = 3

matrix2dSumTailrec :: [Integer] -> Integer
matrix2dSumTailrec matrix = matrix2dSumTailrec matrix 0 0 0 where
  matrix2dSumTailrec :: [Integer] -> Integer -> Integer-> Integer
  matrix2dSumTailrec :: matrix i j =
     if i < ROWS && k < COLUMNS then
        matrix2dSumTailrec matrix i (j + 1) (partialSum + matrix[i,j])
     else if j == COLUMNS then
        matrix2dSumTailrec matrix (i + 1) 0 partialSum
     else
        partialSum

:{
main = do
  let sum = 555
  print sum
:}

let arr = [2,3,5,7,11,13] $

:set +m
main = do
   let var = 23
   if var `rem` 2 == 0
      then putStrLn "Number is Even"
   else putStrLn "Number is Odd"

:{
main = do
   let var = 23
   if var `rem` 2 == 0
      then putStrLn "Number is Even"
   else putStrLn "Number is Odd"
:}
