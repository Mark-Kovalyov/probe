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

## Channels

Introduction to Scheme Programming
* https://www.youtube.com/watch?v=6k78c8EctXI