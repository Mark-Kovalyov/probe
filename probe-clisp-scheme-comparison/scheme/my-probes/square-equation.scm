;;; mayton Dec-27 2020
(define (quadratic-equation a b c)
    (define (determinant a b c)
        ( - (* b b) (* 4 a c) ) )
    (if (< (determinant a b c) 0)
       (error "Complex roots!")
       (list (/ (- (- b) (sqrt (determinant a b c))
                (* 2 a) )
             (/ (+ (- b) (sqrt (determinant a b c))
                (* 2 a) ) ) ) ) ) )

(quadratic-equation 1.0 -8.0 12.0)

;;; The same with let
(define (quadratic-equation a b c)
    (let ((d ( - (* b b) (* 4 a c) )))
        (if (< d 0)
           (error "Complex roots!")
           (list (/ (- (- b) (sqrt d)
                    (* 2 a) ) )
                 (/ (+ (- b) (sqrt d)
                    (* 2 a) ) )
           )
        )
    )
)


