-- Class types

Prelude> :t 3
3 :: Num t => t

Prelude> :t 3.0
3.0 :: Fractional t => t

Prelude> let x = 3 :: Int

Prelude> :t x
x :: Int

Prelude> let i = 777777777777777777777777777777777777 :: Integer
Prelude> :t i
i :: Integer


