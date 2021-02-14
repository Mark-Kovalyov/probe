;;; Reverse
;;; Input:
;;;  (1 2 3)
;;; Output:
;;;  (3 2 1)

(define (mtn-rev x)
   (cond
     ((null? x) '())
     ((null? (cdr x)) (list (car x)))
     (else (cons (last x) (mtn-rev (mtn-all-but-last x))))
   )
)
