-- This is sample tutorial from https://www.haskell.org/

primes = filterPrime [2..]
  where filterPrime (h:t) =
          h : filterPrime [x | x <- t, x `mod` h /= 0]

