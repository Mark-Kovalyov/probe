-- Расширение класса

class Eq a where
  ....

class (Eq a) => Ord a where
  (<) :: a -> a -> Bool
  (<=) :: a -> a -> Bool
  (>=) :: a -> a -> Bool
  (>) :: a -> a -> Bool
  max :: a -> a -> a
  min :: a -> a -> a
  compare :: a -> a -> Ordering

class (Eq a, Printable a) => MyClass a where
  ...
