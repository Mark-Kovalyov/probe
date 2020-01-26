-- Guard expressions


factorial4   n | n == 0    = 1
               | n > 0     = n * factorial4(n - 1)
               | otherwise = error "Negative!"
