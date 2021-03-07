-- Infinite countdown

(defun countdown (n)
  (if (>= n 0 )
    (cons n (countdown(- n 1)))))

-- Infinite fibonacci

(defun fibo(a b)
    (cons a (fibo (b (+ a b))))
)

-- Limited Fibonacci

(defun fibo(a b)
    (cons 	a 
	    (if (< a 2147483647) 
		(fibo b (+ a b))
		NIL)))

