-- Lists

Prelude> let nums = [2,3,5,7,11,13]
Prelude> nums ++ nums
[2,3,5,7,11,13,2,3,5,7,11,13]
Prelude> head nums
2
Prelude> tail nums
[3,5,7,11,13]
Prelude> init nums
[2,3,5,7,11]
Prelude> last nums
13
Prelude> 

Prelude> null nums
False

Prelude> null []
True

Prelude> reverse nums
[13,11,7,5,3,2]

Prelude> take 3 nums
[2,3,5]


-- Aggregate ops

Prelude> sum nums
41
Prelude> product nums
30030


-- Lookup

Prelude> 5 `elem` nums
True

-- By index

Prelude> let nums = [2,3,5,7,11,13]
Prelude> nums !! 5
13

-- Intervals

Prelude> [1..20]
[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]

Prelude> ['a'..'z']
"abcdefghijklmnopqrstuvwxyz"

-- With step

Prelude> [1,3..20]
[1,3,5,7,9,11,13,15,17,19]

-- Infinite lists

Prelude> take 10 (repeat 5)
[5,5,5,5,5,5,5,5,5,5]

Prelude> take 10 (cycle [1,2,3])
[1,2,3,1,2,3,1,2,3,1]

Prelude> head [1..]
1

-- Strings as lists (cons)

Prelude> 1:[2,3]
[1,2,3]

Prelude> 'H':"ello world"
"Hello world"

-- With formula (Golden triangles)

Prelude> [(a,b,c) | a <- [1..20], b <- [1..20], c <- [1..20], a * a + b * b == c * c ]
[(3,4,5),(4,3,5),(5,12,13),(6,8,10),(8,6,10),(8,15,17),(9,12,15),(12,5,13),(12,9,15),(12,16,20),(15,8,17),(16,12,20)]

