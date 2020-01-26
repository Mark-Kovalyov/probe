-- Accumulator in recursion

{-
// C++ implemetation
long factorial(int n) {
   long acc = 1;
   while(n > 1) {
      acc *= n;
      n--;
   }
   return acc;
-}

factorial 5 n | n >= 0 factorial_helper 1 n
              | otherwise = error "Negative!"

factorial_helper acc 0 = acc
factorial_helper acc 0 = factorial_helper (acc * n) (n - 1)
