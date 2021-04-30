myReverse [] = []
myReverse [f,middle,t] = [last t, rev middle, head f]

myReverse "A man, a plan, a canal, panama!"
"!amanap ,lanac a ,nalp a ,nam A"
