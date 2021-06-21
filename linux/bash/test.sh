#!/bin/bash -v

animals=( ["moo"]="cow" ["woof"]="dog")

echo "All : ${animals[@]}"

echo "Who says moo  = ${animals['moo']}"
echo "Who says woof = ${animals['woof']}"

