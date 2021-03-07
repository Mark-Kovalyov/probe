The same source code with Haskell language

> countLinesInFiles :: [FilePath] -> IO [Int]
> countLinesInFiles = traverse (fmap (length . lines) . readFile)

Description for functions:

> readFile :: FilePath -> IO String

> lines :: String -> [String]

> (.) :: (b -> c) -> (a -> b) -> a -> c

> length :: Foldable t => t a -> Int

> fmap :: Functor f => (a -> b) -> f a -> f b

> traverse
>  :: (Applicative f, Traversable t) => (a -> f b) -> t a -> f (t b)

Books:
 Will Kurt - Get Programming with Haskell
 Manning Publications, 2018-Mar

