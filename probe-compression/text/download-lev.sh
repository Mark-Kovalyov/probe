#!/bin/bash -v

rm war-and-society-1-2-3-4.utf-8.txt

for id in 1 2 3 4;
do
  gzip -k -f "war-and-society-$id.utf-8.txt"
  cat "war-and-society-$id.utf-8.txt" >> "war-and-society-1-2-3-4.utf-8.txt"
done;

gzip -k -f war-and-society-1-2-3-4.utf-8.txt


