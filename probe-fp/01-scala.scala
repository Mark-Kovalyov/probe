// Fibonacci unlimited 

def fibo(a : Int, b : Int) : Stream[Int] = a #:: fibo(b, a + b) 

// FIbinacci till MAX int

val GOLDEN_RATIO = 1.0 / ((1.0 + Math.sqrt(5.0)) / 2.0)

def fibo(a : Int, b : Int) : Stream[Int] = a #:: (if (a < 0x7FFFFFFF) fibo(b, a + b) else Stream.empty)

fibo(1,1).force

// 
// 10 ^ 308 .... 2 ^ 2048

// 10 ^ 3        2 ^ 10 =                     1 024
// 10 ^ 5        2 ^ 16 =                    16 384
// 10 ^ 10       2 ^ 31 =             2 147 483 648
// 10 ^ 19       2 ^ 63 = 9 223 372 036 854 775 807
// 	
