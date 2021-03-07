#!/bin/bash -v

# 30-Jul 2020, mayton

# -undefined dynamic_lookup

clang \
  -g \
  -shared \
  -o rnb-tree.so \
  rnb-tree.c

echo "[1] Status : $?"

if [ $? -ne 0 ]; then
  >&2 echo "command failed with exit code $?"
  exit $?
fi

clang \
  -g \
  -shared \
  -o ff-b-tree.so \
  ff-b-tree.c

echo "[2] Status : $?"

rm benchmark.exe

clang \
  -g \
  -L rnb-tree \
  -l rnb-tree.so \
  benchmark.c -o \
  benchmark.exe \
 && \
 ./benchmark.exe

echo "[3] Status : $?"
