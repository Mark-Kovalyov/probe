-- Let in

module Demo where

roots2 :: Double -> Double -> Double -> (Double, Double)

roots2 a b c =
 let d = sqrt(b^2 - 4*a*c) in
 ((-b - d) / (2*a), (-b + d) / (2*a))


-- Let in with bracers
roots3 :: Double -> Double -> Double -> (Double, Double)
roots3 a b c =
 let { d = sqrt(b^2 - 4*a*c); x1 = (-b - d) / (2*a);  x2 = (-b + d) / (2*a); }
 in (x1, x2)

-- With indents.
-- Отступы должны быть одинаковы!
roots4 :: Double -> Double -> Double -> (Double, Double)
roots4 a b c =
 let
    x1 = (-b - d) / twicea
    x1 = (-b + d) / twicea
    d  = sqrt(b^2 - 4*a*c)
    twicea = 2 * a
 in (x1, x2)
