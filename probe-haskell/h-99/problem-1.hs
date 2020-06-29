main = print (myLast [1,2,3])

myLast :: [a] -> a
myLast []     = error "Unable to get last from []"
myLast (h:[]) = h
myLast (h:t)  = if (null (tail t)) then (head t) else (myLast t)
