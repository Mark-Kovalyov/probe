#!/bin/bash -v

clang \
  -shared \
  -undefined dynamic_lookup \
  -o ff-b-tree-ii.so \
  ff-b-tree.c

echo "Status : $?"


