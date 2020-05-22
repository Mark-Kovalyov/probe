data Product = Product { name :: String, category :: String }

data Order = Order { product :: [Product] }

productsByCat :: String -> [Order] -> [Product]