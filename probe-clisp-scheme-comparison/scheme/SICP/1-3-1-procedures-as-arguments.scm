;;; Ariphmetic progrssion from a to b

(define (sum-integers a b)
  (if (> a b)
     0
     (+ a (sum-integers (+ a 1) b))
  )
)

;;; Pi number ~ Pi/8^49

(define (sum-pi a b)
  (if (> a b)
     0
     (+ (/ 1.0 (* a (+ a 2))) (sum-pi (+ a 4) b))
  )
)

;;; The generic schema

(define (<name> a b)
  (if (> a b)
     0
     (+ (<term> a)
        (<name> (<next> a) b))))


