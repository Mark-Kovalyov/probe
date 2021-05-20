# Heap

Correct heap
```
 Correct heap: {94, 67, 55, 44, 42, 18, 12, 6 };
    
            94               0
          /   \            /   \
         67   55          1     2
        / \   / \        / \   / \
       44 42 18 12      3   4 5   6
      /                /
     6                7
```

Invariants:
```
a[i] >= a[i*2]
a[i] >= a[i*2 + 1]
```


## Links 

* https:www.youtube.com/watch?v=t0Cq6tVNRBA