{-| 14 -> 3
    15 -> 3
    16 -> 4 
-}


bls
===
1 15 15
1 

:{
lls :: Integer -> Integer -> Integer
lls a b = if a * a >= b then a else lls (a + 1) b
:}

// 0 15 15 _


:{
// x   x^2
// 2.0 
// 2.0 
// 
blsd :: Double -> Double -> Double
blsd x guess1 guess2 = 
  if abs(guess1 - guess2) < 0.0001 
     then guess1
  else 
     if (x - guess1 * guess1 > 0) then 
        blsd x guess2 (x - guess1) 
     else
        blsd x guess2 (x - guess1) 
}

:{
bls :: Integer -> Integer -> Integer
bls guess1 guess2 x = 
  if guess
  if guess * guess > x then 
      bls guess $ quot 2 guess
  else 
      guess
:}  
      

slr :: Integer -> Integer 
slr x | x < 0  = error "Unable to take root from negative"
      | x == 0 = 0
      | otherwise = lls 1 x                  
:}
