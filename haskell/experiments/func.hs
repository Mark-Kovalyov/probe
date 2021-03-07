size = 5
half = size `div` 2

-- |The 'Mars' magic square

11 24 7  20 3
4  12 25 8  16
17 5  13 21 9
10 18 1  14 22
23 6  19 2  15 

-- |Check! = 169

sum [7] + sum [12,25,8] + sum [17, 5, 13, 21, 9] + sum [18, 1, 14] + sum[19]

arr = [ 1,  2,  3,  4,  5, 6,  7,  8,  9,  11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27 ]

([]) :: [ Integer ] -> Integer -> Integer -> Integer

:{
matrix :: [Integer] -> Integer -> Integer -> Integer -> Integer
matrix arr i j size = arr !! (size * i + j)
:}

calc_summ :: [Integer] -> Integer -> Integer -> Integer -> Integer

:{
calc_summ arr accumulator i j 
  | i < size = calc_summ accumulator + arr[i][j] ( i + 1 ) j
  | i == size && calc_summ = accumulator + arr[i][j] 0 ( j + 1 )
  | i == size && j == size = otherwise accumulator + arr[i][j]
  | otherwise = accumulator + arr[i][j]
:}

:{
factorial4   n | n == 0    = 1
               | n > 0     = n * factorial4(n - 1)
               | otherwise = error "Negative!"
:}


calcSumm(int accumulator, int i, int j) =
   if ( i < size ) {
      calcSumm(accumulator + arr[i][j], i + 1, j);
   } else {
      calcSumm(accumulator + arr[i][j], 0, j + 1);
   } else {
      return accumulator + arr[i][j];
   }





