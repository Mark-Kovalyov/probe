;;; From http://chronicles.blog.ryanrampersad.com/2011/11/scheme-split-a-list/
(define (mtn-split lst at)
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
