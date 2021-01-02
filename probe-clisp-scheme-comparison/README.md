# Common Lisp vs MIT-Scheme

## Define function

Scheme
```
(define (ip-to-list ip) ip)

ex:

(ip-to-list "192.168.0.1")

(192 168 0 1)
```

Lisp
```
(defun parse-ip(ip) ip)
```