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
  ;; '(3 (1 2 3 4 5))
  (define (perm-k-n k n current) ;; (3 5 (1 2 3 4 5))
     (if (< k (- n 1))
        (cons (current) 
              (perm-k-n k (- n 1) (cdr current)))
     )
  )
  (perm-k-n k n (gen-seq n))
)

; (
;  (1 2 3 4 5)
;  (2 3 4 5)
;  (3 4 5)
; )



(define (perm-k-n-stub k n)
  (cons '(1 2 3) 
      (cons '(1 2 4) 
         (cons '(1 3 4) 
            (cons '(2 3 4) '())))))

(define (perm-k-n-stub k n)
  (cons-stream '(1 2 3) 
      (cons-stream '(1 2 4) 
         (cons-stream '(1 3 4) 
            (cons-stream '(2 3 4) the-empty-stream)))))

;;; From http://chronicles.blog.ryanrampersad.com/2011/11/scheme-split-a-list/
(define (split lst at)
  ; splits the list
  ; n - new list
  ; l - an old
  ; i - a counter
  (define (iter n l i)
    (if (or (null? l) (= i at))
      (cons (reverse n) l)
      (iter (cons (car l) n) (cdr l) (+ i 1))
    )
  )
  (iter () lst 0)
)  

;;; Anatolyi's source

; const size_t k;
; const size_t n;
; const tail = n - k
; curr = { 1,2,3,4 }

;generate(0,0);
;void generate(int pos, int val) noexcept
;    {
;        const int limit = n - k + pos + 1;
;        while (val < limit) {
;            curr[pos] = val;
;            if (pos == k - 1) {
;                print(curr)
;            } else {
;                generate(pos + 1, val + 1);
;            }
;            val++;
;        }
;    }



        for (; val < limit; val++) {
            curr[pos] = val;
            if (pos == last_pos) {
                count++;
                print_current();    
            }
            else {
                count = generate(pos + 1, val + 1, count);
            }
        }


        while (val < limit) {
            curr[pos] = val;
            if (pos == last_pos) {
                count++;
                print_current();    
            } else {
                count = generate(pos + 1, val + 1, count);
            }
            val++
        }