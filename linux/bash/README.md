# Bash

## Hashmaps

```
#!/bin/bash -v

animals=( ["moo"]="cow" ["woof"]="dog")

echo "Who says moo  = ${animals['moo']}"
echo "Who says woof = ${animals['woof']}"
```