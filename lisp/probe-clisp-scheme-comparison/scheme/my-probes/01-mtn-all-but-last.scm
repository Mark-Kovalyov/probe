;;; all-but-last
;;; (1 2) => (1)
;;; (1) => ()
;;; () => caught error

(define (mtn-all-but-last x)
  (cond ((null? x) (error "Unable to get get sublist from null"))
        ((null? (cdr x)) '())
        (else (cons (car x) (mtn-all-but-last (cdr x))))
  )
)
