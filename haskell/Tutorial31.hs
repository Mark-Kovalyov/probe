-- Where

roots2 a b c = (x1, x2) where
 x1 = (-b - d) / atwice
 x2 = (-b + d) / atwice
 d = sqrt $ b^2 - 4 * a * c
 atwice = 2*a

-- Let может возвращать результат

Prelude> let x = 2 in x^2
4
Prelude> (let x = 2 in x^2)^2
16
Prelude>

-- В противоположность Where не возвращает


