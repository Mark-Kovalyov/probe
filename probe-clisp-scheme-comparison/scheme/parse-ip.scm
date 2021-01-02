
(define (atom? x)
  (and (not (null? x))
       (not (pair? x))))

(define (ip-to-list ip)
  (atom? ip))

(define (ip-to-list ip)
  (if (and (atom? ip) (string? ip))
     (substring ip 1 3)
     (error "Not an atom!")
  )
)

;;  Transforms IP address string into list
;;; Input : "4.17.81.176"
;;; Out : (4 17 81 176)
(define (ip-to-list ip)
  ;; "4.17.81.176" () 0 1
  (define (ip-to-lisp-rec ip ip-cumulative-list from-val to-val length)
      (if (and (eq from-val length) (eq to-val length))
         (ip-cumulative-list)
         ....
      )
  )
  (if (and (atom? ip) (string? ip))
     (ip-to-lisp-rec ip () 0 1 (string-length ip))
     (error "Not an atom!")
  )
)

(ip-to-list "4.17.81.176")