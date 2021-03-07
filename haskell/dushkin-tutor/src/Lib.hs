module Lib
    ( someFunc
    ) where

someFunc :: IO ()
someFunc = putStrLn "someFunc"

myseq :: Int -> [Int]
myseq 0 = []
myseq 1 = [1]
myseq 2 = 1 : [2]

mylen :: [a] -> Int
--mylen [] = 0
--mylen (h:t) = 1 + mylen t
mylen list = if (null list) then 0 else 1 + length (tail list)
