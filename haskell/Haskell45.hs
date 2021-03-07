-- Enum, Bounded

module Demo where

class Enum a where
  succ :: a -> a
  pred :: a -> a
  toEnum :: Int -> a
  fromEnum :: a -> Int

class Bounded a where
  minBound :: a
  maxBound :: a
