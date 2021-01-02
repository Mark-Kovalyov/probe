;;; Recursive factorial,
;;; Language: MIT/GNU Scheme
;;; Dec-20, 2020 : mayton

(define (fact x)
   (cond
      ((= x 0) 1)
      ((= x 1) 1)
      (else (* x (fact (- x 1))))
   )
)

;;; The same, with tail recursion

(define (fact-tail x)
  (define (fact-internal partial-product x)
     (if (> x 1)
       (fact-internal (* x partial-product) (- x 1))
       partial-product
     )
  )
  (fact-internal 1 x)
)
