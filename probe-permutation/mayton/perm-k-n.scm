;;; Permutation k of n

;;; (perm-k-n 3 `(1 2 3 4))

(define (gen-seq n)
  (define (gen-seq n lst)
    (if (= n 0)
       lst
       (gen-seq (- n 1) (cons n lst))
    )
  )
  (gen-seq n '())  
)

;;; In:
;;; 3 (1 2 3 4)

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

(define (perm-k-n k n)
  ;; '(3 (1 2 3 4))
  (define (perm-k-n k nlist)
     ;; '(3 (1 2 3 4) () )
     ;;  '(2 (1 2 3 4) (1)
     ;;   '(1 (1 2 3 4) (1 2)
     ;;    '(0 (1 2 3 4) (1 3 3)
     (define (perm-k-n k nlist curr tail)
        (if (>= 0 k)
           curr
           (perm-k-n (- k 1) nlist (cons 
        )
     )     
     (perm-k-n k nlist '() '())
  )
  (perm-k-n k (gen-seq n))
)

;;; Anatolyi's source

generate(0,0);

void generate(size_t pos, size_t val) noexcept
    {
        const size_t limit = n - k + pos + 1;
        for (; val < limit; val++) {
            curr[pos] = val;
            if (pos == k - 1) {
                print_current();    
            }
            else {
                generate(pos + 1, val + 1);
            }
        }
    }
