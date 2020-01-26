-- Factorial with local helper definition

factorial6 n
   | n >= 0 = let
        helper acc 0 = acc
        helper acc n = acc helper (acc * n) (n - 1)
     in helper 1 n
   | otherwise = error "Negative!"