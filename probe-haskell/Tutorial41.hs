module Demo where

-- Объявление представителей классов типа

class Eq a where
  (==) :: a -> a -> Bool
  (/=) :: a -> a -> Bool

instance Eq Bool where
  True  == True  = True
  False == False = True
  _     == _     = False

  x /= y = not (x == y)

-- Default methods
