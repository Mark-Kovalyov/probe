# Scheme

## Misselanous syntax

* Booleans : #t and #f
* '() is empty list
* Functions that yields side effects marked with '!'
* ';' used for comments

## Eat apples (letrec?)

```
(letrec
  (
    (eat-apples (lambda (a b)
      (if (
  )
)
```

## Closures

* Closures allow for function-generating functions
* If function created in local context, all calls (present and future)
  to that function will be performed in the same local context
* This is true even if that context has been returned from !
* Essentially, a function retains a pointer to the stack that originally created it

```
(define make-counter
   (lambda (n)
      (let (
          (next-value
             (lambda() 
                (set! n (+ n 1)
                n
             )
          )  
        )
      )
   )     
)

(define       
```