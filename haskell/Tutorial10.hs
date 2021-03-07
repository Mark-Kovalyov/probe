--

let discound limit proc sum = if sum >= limit then sum * (100 - proc) / 100 else sum

let standardDiscound = discound 1000 5
