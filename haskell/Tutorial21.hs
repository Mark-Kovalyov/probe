-- Lists

Prelude> [1,2,3]
[1,2,3]

Prelude> ["Hello","World"]
["Hello","World"]

Prelude> :t [1,2,3]
[1,2,3] :: Num t => [t]

-- Char list equal to String
Prelude> :t ['H','e']
['H','e'] :: [Char]
Prelude> :t "Hello"
"Hello" :: [Char]
