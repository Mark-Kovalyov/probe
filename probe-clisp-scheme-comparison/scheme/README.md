# Scheme

## Scheme predicates

|x      |null? |pair? | equal? | car | cdr |
|-------|------|------|--------|-----|-----|
|()     | true | false|
|(a)    | false| true |
|(a b)  | false| true |
|(a b c)| false| true |

## Equality



# Scheme string functions

## Check atom for string
```
> (string? "Hello")

;Value: #t

> (string? 1)

;Value: #f

```

## String to list
```
> (string->list "Hello")

;Value: (#\H #\e #\l #\l #\o)

```

## Substring

```
(substring "Hoola-hoola" 2 5)        

;Value: "ola"
```

## String equals

```

```

##