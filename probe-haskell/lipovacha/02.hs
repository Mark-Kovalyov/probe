-- Typeclass

Prelude> :t (==)
(==) :: Eq a => a -> a -> Bool

Prelude> :type show
show :: Show a => a -> String

Prelude> show 1
"1"
Prelude> show "Hello"
"\"Hello\""
Prelude> :t show 1
show 1 :: String
Prelude> :t show "Hello"
show "Hello" :: String

