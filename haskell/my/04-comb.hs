{-|
Input
 1 2

Output
 [1,2]


Input:
 3 4
  3 [1,2,3,4]
   3 [1] , [2,3,4] 
    3 [1,2], [3,4]
     3 [1,2,3], [4] -> print [1,2,3]
    3 [1,2], [4]
     3 [1,2,4], [] - print [1,2,4]
   3 [1] , [


Output

 1 2 3
 1 2 4
 1 3 4
 2 3 4
-}


comb :: Int -> Int -> [Int]
comb m n = comb' m [1..n]


{- TODO --}
comb' :: Int -> [Int] -> [Int]
comb' n list = n : list





