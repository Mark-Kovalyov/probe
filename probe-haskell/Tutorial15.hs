module Demo where

-- Определенеи бинарного оператора
f $ x = f x

sin 0
0.0
sin $ 0
0.0

-- Оператор $ - самый низкий приоритет

sin (pi / 2)

sin $ pi / 2

--

{- f (g x (h y)) == f $ g x (h y) == f $ g x $ h y -}


