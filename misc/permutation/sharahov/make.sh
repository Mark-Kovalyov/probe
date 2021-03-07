#!/bin/bash

rm -f perm-k-n.exe

fpc -CX -O3 -XX -vewnhi -Fi. -Fu. -FU. perm-k-n.lpr \
  && mv perm-k-n perm-k-n.exe

./perm-k-n.exe


