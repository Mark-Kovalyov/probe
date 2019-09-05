// Fibonacci unlimited 

def fibo(a : Int, b : Int) : Stream[Int] = a #:: fibo(b, a + b) 

// FIbinacci till MAX int

val GOLDEN_RATIO = 1.0 / ((1.0 + Math.sqrt(5.0)) / 2.0)

def fibo(a : Int, b : Int) : Stream[Int] = a #:: (if (a < 0x7FFFFFFF) fibo(b, a + b) else Stream.empty)

fibo(1,1).force

// Factorial

def fact(a : Int) : Int = {
	var prod = 1
	for(x <- 1 to a) prod = prod * x
	prod
}

def fact(x: BigInt): BigInt = {

    @tailrec 
    def facttail(x: BigInt, accum: BigInt): BigInt =
      if (x == 0)
        accum
      else
        facttail(x - 1, accum * x)

    facttail(x, 1)
}

