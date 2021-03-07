#!/usr/bin/ghci

(*) Find the last element of a list.
(Note that the Lisp transcription of this problem is incorrect.)
Example in Haskell:

myLast [1,2,3,4]
4
myLast ['x','y','z']
'z'

> main = print (myLast [1,2,3])
Assume that input type is list of 'a' type and output is 'a' type.
> myLast :: [a] -> a
Empty input will throws an error.
> myLast []     = error "Unable to get last from []"
Last element will be first when tail is empty
> myLast (h:[]) = h
Otherwise, it tail of tail is empty then get head of tile, Otherwise proceed 'myLast' for tile
> myLast (h:t)  = if (null (tail t)) then (head t) else (myLast t)
