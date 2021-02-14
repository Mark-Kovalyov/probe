;;; Permutation k of n

;;; mayton - 25-Jan 2021

;;; (perm-k-n 3 `(1 2 3 4))

(define (gen-seq n)
  (define (gen-seq n lst)
    (if (= n 0)
       lst
       (gen-seq (- n 1) (cons n lst))))
  (gen-seq n '()))

;;; In:
;;; 2 3
;;; OUt:
;;; (
;;;  (1 2)
;;;  (2 3)
;;;  (3 4)
;;; )


;;; In:
;;; 3 5
;;; (1 2 3 4 5)

;;; curr = (1) tail = (2 3 4 5) len = 1 k = 3
;;;    curr = (1 2) tail = (3 4 5) len = 2
;;;       curr = (1 2) (3) tail = (4 5) len = 3   : (1 2 3)
;;;       curr = (1 2) (4) tail = (5) len = 3     : (1 2 4)
;;;       curr = (1 2) (5) tail = () len = 3      : (1 2 5)
;;;    curr = (1 3) tail = (4 5) len = 2
;;;       curr = (1 3) (4) tail = (5) len = 3     : (1 3 4)
;;    ................
;;;   ................
;;;       
;;;       curr = (3 4) (5) tail = () len = 3      : (3 4 5)

(define (perm-k-n k n)
  (define (iterate curr tail currlen k)

  )
  (let ((curr (seq n)))
    (iterate (car curr) (cdr curr) currlen k)
  )
)


;;; OUt:
;;; (
;;;  (1 2 3) 
;;;  (1 2 4)
;;;  (1 3 4)
;;;  (2 3 4)
;;; )
;;; TODO: Improove with cons-stream, stream-car, stream-cdr... 
;;;       the-empty-stream, stream-null?
;;;       stream-map, stream-ref, stream-filter
;;;
;;; TODO: Parallelize
;;; TODO: Migrate to SBCL, Lisp. See performance.


;;; Example 1
(define (streamex)
  (cons-stream '(1 2 3)
    (cons-stream '(1 2 4)
      (cons-stream '(1 2 5)
           the-empty-stream)))
)
;;; Will return Value: {(1 2 3) ...}

;;; With force
